package com.smileydev.recipez.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.smileydev.recipez.R;
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.User;

import java.util.ArrayList;
import java.util.Date;

public class AddRecipe extends AppCompatActivity {

    private int recipeId;

    private String name;

    private Date dateCreated;

    private Date lastUpdate;

    private int pplServed;

    private int timeEstimate;

    private User createdBy;

    private ArrayList<String> instructions;

    private ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}