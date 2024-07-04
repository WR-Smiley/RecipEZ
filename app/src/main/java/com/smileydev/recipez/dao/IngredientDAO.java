package com.smileydev.recipez.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.smileydev.recipez.entities.Ingredient;

import java.util.List;

@Dao
public interface IngredientDAO {

    @Insert(onConflict = 3)
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT * FROM INGREDIENT ORDER BY name")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM INGREDIENT WHERE name == :name")
    Ingredient getIngredient(String name);
}
