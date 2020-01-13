package com.example.shan.dbapp1;

public class User {
    private int id;
    private String uname;
    private String email;

    public User(int id, String name, String email){
        this.id = id;
        this.uname = name;
        this.email = email;
    }

    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return this.uname;
    }
    public String getEmail(){
        return this.email;
    }
}
