package com.smileydev.recipez.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = 3)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM USER ORDER BY name")
    List<User> getAllUsers();

    @Query("SELECT * FROM USER WHERE id = :id")
    User getUser(int id);
}
