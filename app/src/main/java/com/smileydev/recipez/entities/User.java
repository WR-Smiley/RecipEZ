package com.smileydev.recipez.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity
public class User extends BasicInfo {

    @NonNull
    private String name;

    @NonNull
    private Date dateCreated;

    @NonNull
    private Date lastUpdate;

    // Head chef, sous-chef, line cook, etc.
    @NonNull
    private String title;

    @NonNull
    private String email;

    @NonNull
    private String username;

    @NonNull
    private String password;

    public User(String name, Date dateCreated, Date lastUpdate, String title, String email, String username, String password) {
        super(name, dateCreated, lastUpdate);
        this.title = title;
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
