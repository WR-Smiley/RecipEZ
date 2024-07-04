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
}
