package com.smileydev.recipez.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class User extends BasicInfo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(@NonNull Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @NonNull
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(@NonNull Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String toString() {
        return this.name + ", " + this.title;
    }
}
