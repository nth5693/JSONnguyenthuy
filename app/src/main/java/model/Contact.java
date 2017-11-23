package model;

/**
 * Created by admin on 11/22/2017.
 */

public class Contact {
    private String id;
    private String name;
    private String email;
    private String address;
    private String gender;

    public Contact() {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.id+"\n"+this.name+"\n"+email+"\n"+address+"\n"+gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress(String address) {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender(String gender) {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
