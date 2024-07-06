package com.smileydev.recipez.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.smileydev.recipez.entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert(onConflict = 3)
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("SELECT * FROM RECIPE WHERE userId = :userId ORDER BY name")
    List<Recipe> getAllRecipes(int userId);

    @Query("SELECT * FROM RECIPE WHERE id = :id AND userId = :userId")
    Recipe getRecipe(int userId, int id);
}
