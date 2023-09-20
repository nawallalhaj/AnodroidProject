package com.example.nawalproj.Classes;

public class User {
    private String uid;
    private String username;
    private String email;
    private String password;
    public User(String email, String password, String uid, String username){
        this.email=email;
        this.password=password;
        this.uid=uid;
        this.username=username;
    }
}
