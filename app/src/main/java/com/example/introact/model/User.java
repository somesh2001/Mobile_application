package com.example.introact.model;

import java.io.Serializable;

public final class User implements Serializable {
    private String name;
    private String password;
    private String email;
    private String contact;

    public User(){};

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String name, String email,String password, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
