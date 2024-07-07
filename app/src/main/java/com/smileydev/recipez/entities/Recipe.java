package com.smileydev.recipez.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
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
    private ArrayList<String> instructions;

    @NonNull
    private ArrayList<Ingredient> ingredients;

    public Recipe(String name, Date dateCreated, Date lastUpdate, int pplServed, int timeEstimate, User createdBy, int userId, ArrayList<String> instructions, ArrayList<Ingredient> ingredients) {
        super(name, dateCreated, lastUpdate);
        this.pplServed = pplServed;
        this.timeEstimate = timeEstimate;
        this.createdBy = createdBy;
        this.userId = userId;
        this.instructions = instructions;
        this.ingredients = ingredients;
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
    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(@NonNull ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    @NonNull
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NonNull ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
