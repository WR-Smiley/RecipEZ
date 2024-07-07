package com.smileydev.recipez.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Recipe extends BasicInfo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private int pplServed;

    @NonNull
    private int timeEstimate;

    @NonNull
    private User createdBy;

    @NonNull
    private int userId;

    @NonNull
    private String instructions;

    public Recipe(String name, Date dateCreated, Date lastUpdate, int pplServed, int timeEstimate, User createdBy, int userId, String instructions) {
        super(name, dateCreated, lastUpdate);
        this.pplServed = pplServed;
        this.timeEstimate = timeEstimate;
        this.createdBy = createdBy;
        this.userId = userId;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPplServed() {
        return pplServed;
    }

    public void setPplServed(int pplServed) {
        this.pplServed = pplServed;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    @NonNull
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NonNull User createdBy) {
        this.createdBy = createdBy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(@NonNull String instructions) {
        this.instructions = instructions;
    }
}
