package com.hieunt.jsonnguyenthuy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import model.Contact;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayList<Contact> dsContact;
    ArrayAdapter<Contact> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvContact = findViewById(R.id.lvContact);
        dsContact = new ArrayList<>();
        adapterContact = new ArrayAdapter<Contact>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsContact
        );
        lvContact.setAdapter(adapterContact);
        ContactTask task= new ContactTask();
        task.execute();

    }

    class ContactTask extends AsyncTask<Void,Void,ArrayList<Contact>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterContact.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            adapterContact.clear();
            adapterContact.addAll(contacts);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            ArrayList<Contact> ds = new ArrayList<>();
            try{

                URL url = new URL("https://api.androidhive.info/contacts/");
                HttpURLConnection connection=(HttpURLConnection)  url.openConnection();
                InputStreamReader inputStreamReader= new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!=null){
                    builder.append(line);
                    line=bufferedReader.readLine();
                }
//                JSONArray jsonArray = new JSONArray(builder.toString());
                JSONObject jsonObject = new JSONObject(builder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                for (int i=0;i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);

                    Contact contact=new Contact();
                    if(jsonObject.has("id"))
                        contact.setId(jsonObject.optString("id"));
                    if (jsonObject.has("name"))
                        contact.setName(jsonObject.optString("name"));
                    if (jsonObject.has("email"))
                        contact.setEmail(jsonObject.optString("email"));
                    if(jsonObject.has("address"))
                        contact.setAddress(jsonObject.optString("address"));
                    if(jsonObject.has("gender"))
                        contact.setGender(jsonObject.optString("gender"));
                    ds.add(contact);
                }

            }catch (Exception ex){
                Log.e("LOI",ex.toString());
            }

            return ds;
        }
    }
}