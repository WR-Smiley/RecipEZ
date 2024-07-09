package com.smileydev.recipez.services;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class  TypeConverters {

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        if (list == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        if  (value == null) {
            return null;
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromIngredients(ArrayList<Ingredient> ingredients) {
        if (ingredients == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return gson.toJson(ingredients, type);
    }

    @TypeConverter
    public static ArrayList<Ingredient> toIngredients(String ingredients) {
        if (ingredients == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return gson.fromJson(ingredients, type);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String toUserString(User user) {
        if (user == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @TypeConverter
    public static User fromUserString(String userString) {
        if (userString == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(userString, User.class);
    }

}
