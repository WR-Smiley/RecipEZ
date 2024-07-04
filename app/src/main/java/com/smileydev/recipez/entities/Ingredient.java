package com.smileydev.recipez.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;

@Entity
public class Ingredient extends BasicInfo {

    @NonNull
    private String name;

    @NonNull
    private Date dateCreated;

    @NonNull
    private Date lastUpdate;

    @NonNull
    private int amt;

    // Volume or weight
    @NonNull
    private String measurementType;

    // Grams, Oz, Fl Oz, Cups, Tb, Tsp, ml,
    @NonNull
    private String measurement;

    @NonNull
    private Recipe recipe;

    public Ingredient(String name, Date dateCreated, Date lastUpdate, int amt, String measurementType, String measurement, Recipe recipe) {
        super(name, dateCreated, lastUpdate);
        this.amt = amt;
        this.measurementType = measurementType;
        this.measurement = measurement;
        this.recipe = recipe;
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

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    @NonNull
    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(@NonNull String measurementType) {
        this.measurementType = measurementType;
    }

    @NonNull
    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(@NonNull String measurement) {
        this.measurement = measurement;
    }

    @NonNull
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(@NonNull Recipe recipe) {
        this.recipe = recipe;
    }
}
