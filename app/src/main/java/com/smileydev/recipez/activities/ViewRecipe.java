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
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.services.IngredientViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewRecipe extends AppCompatActivity {

    private int recipeId;

    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_recipe);
        Button editButton = findViewById(R.id.edit);
        TextView recipeName = findViewById(R.id.recipe_name);
        TextView createdUser = findViewById(R.id.creator);
        TextView numServed = findViewById(R.id.recipePplEdit);
        TextView timeEst = findViewById(R.id.timeEdit);
        TextView instruction = findViewById(R.id.instructionEdit);
        TextView creationDate = findViewById(R.id.dateCreated);
        TextView updated = findViewById(R.id.lastUpdate);
        RecyclerView ingredients = findViewById(R.id.ingredientViewRecycler);
        Repository repo = new Repository(getApplication());
        List<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
        for (Ingredient i : repo.getAllIngredients()) {
            if (i.getRecipeId() == recipeId) {
                recipeIngredients.add(i);
            }
        }
        Recipe current = repo.getRecipe(userId, recipeId);
        final IngredientViewAdapter ingredientViewAdapter = new IngredientViewAdapter(this);
        ingredients.setAdapter(ingredientViewAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientViewAdapter.setIngredients(recipeIngredients);


        recipeId = getIntent().getIntExtra("id", -1);
        userId = getIntent().getIntExtra("userId", 1);
        String name = getIntent().getStringExtra("name");
        int serves = getIntent().getIntExtra("ppl", 0);
        int estimate = getIntent().getIntExtra("estimate", 0);
        String createdBy = getIntent().getStringExtra("user");
        String[] instructionList = getIntent().getStringArrayExtra("instructions");
        String instructions = "";
        for (String s : instructionList) {
            instructions += s + "\n";
        }

        recipeName.setText(name);
        createdUser.setText(createdBy);
        numServed.setText(serves);
        timeEst.setText(estimate);
        instruction.setText(instructions);
        creationDate.setText(current.getDateCreated().toString());
        updated.setText(current.getLastUpdate().toString());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRecipe.this, EditRecipe.class);
                intent.putExtra("id", current.getId());
                intent.putExtra("userId", current.getUserId());
                intent.putExtra("name", current.getName());
                intent.putExtra("create", current.getDateCreated());
                intent.putExtra("updated", current.getLastUpdate());
                intent.putExtra("ppl", current.getPplServed());
                intent.putExtra("estimate", current.getTimeEstimate());
                intent.putExtra("user", current.getCreatedBy().getClass().getName());
                intent.putExtra("instructions", current.getInstructions());
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}