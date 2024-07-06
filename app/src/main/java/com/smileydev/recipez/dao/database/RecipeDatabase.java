package com.smileydev.recipez.dao.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.smileydev.recipez.dao.IngredientDAO;
import com.smileydev.recipez.dao.RecipeDAO;
import com.smileydev.recipez.dao.UserDAO;
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

@Database(entities = {Recipe.class, Ingredient.class, User.class}, version = 1, exportSchema = false)
@TypeConverters({com.smileydev.recipez.services.TypeConverters.class})
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDAO mRDao();
    public abstract IngredientDAO mIDao();
    public abstract UserDAO mUDao();
    private static volatile RecipeDatabase INSTANCE;

    static RecipeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized(RecipeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RecipeDatabase.class,"RecipEZ.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
