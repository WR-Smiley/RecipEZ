package com.smileydev.recipez.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;
import com.smileydev.recipez.dao.database.Repository;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.services.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Button newRecipe = findViewById(R.id.addRecipe);
        TextView welcome = findViewById(R.id.welcomeMsg);
        userId = getIntent().getIntExtra("userId", -1);
        String userName = getIntent().getStringExtra("userName");
        welcome.setText("Welcome to RecipEZ, " + userName + "!");
        RecyclerView recipeRecycler = findViewById(R.id.recipeRecyclerView);
        Repository repo = new Repository(getApplication());
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe r : repo.getAllRecipes(userId)) {
            recipes.add(r);
        }
        final RecipeAdapter recipeAdapter = new RecipeAdapter(this, getApplication());
        recipeRecycler.setAdapter(recipeAdapter);
        recipeRecycler.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter.setRecipes(recipes);


        newRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRecipe = new Intent(Homepage.this, AddRecipe.class);
                addRecipe.putExtra("userId", userId);
                addRecipe.putExtra("userName", userName);
                startActivity(addRecipe);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recipeRecycler = findViewById(R.id.recipeRecyclerView);
        Repository repo = new Repository(getApplication());
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe r : repo.getAllRecipes(userId)) {
            recipes.add(r);
        }
        final RecipeAdapter recipeAdapter = new RecipeAdapter(this, getApplication());
        recipeRecycler.setAdapter(recipeAdapter);
        recipeRecycler.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter.setRecipes(recipes);

    }
}
