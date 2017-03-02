package com.gert.model;


/**
 * Created by gert on 02.03.17.
 */
public class WebUser {

    private int id;
    private String fName;
    private String lName;
    private String email;

    public WebUser() {
    }

    public WebUser(String name, String email, String address, String telephone) {
        this.fName = name;
        this.email = email;
        this.lName = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
