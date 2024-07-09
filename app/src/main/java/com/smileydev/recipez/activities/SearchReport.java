package com.smileydev.recipez.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import com.smileydev.recipez.services.RecipeSearchAdapter;

import java.util.List;

public class SearchReport extends AppCompatActivity {

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_report);
        Repository repo = new Repository(this);
        Button home = findViewById(R.id.homeButton);
        String query = getIntent().getStringExtra("query");
        userId = getIntent().getIntExtra("userId", -1);
        RecyclerView searchRecycler = findViewById(R.id.searchResults);

        // Populate search results into recycler report
        List<Recipe> searchResults = repo.searchRecipes(userId, query);
        final RecipeSearchAdapter recipeSearchAdapter = new RecipeSearchAdapter(getApplicationContext(), getApplication());
        searchRecycler.setAdapter(recipeSearchAdapter);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recipeSearchAdapter.setRecipes(searchResults);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void returnHome() {
        Intent intent = new Intent(SearchReport.this, Homepage.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userName", getIntent().getStringExtra("userName"));
        startActivity(intent);
    }

}