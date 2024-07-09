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
import com.smileydev.recipez.services.IngredientRecipeViewAdapter;

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
        Button share = findViewById(R.id.share);
        TextView recipeName = findViewById(R.id.recipe_name);
        TextView createdUser = findViewById(R.id.creator);
        TextView numServed = findViewById(R.id.recipePplEdit);
        TextView timeEst = findViewById(R.id.timeEdit);
        TextView instruction = findViewById(R.id.instructionEdit);
        TextView creationDate = findViewById(R.id.dateCreated);
        TextView updated = findViewById(R.id.lastUpdate);
        RecyclerView ingredients = findViewById(R.id.ingredientViewRecycler);
        // Set recipeId and userId before any logic to ensure all subsequent logic works.
        recipeId = getIntent().getIntExtra("id", -1);
        userId = getIntent().getIntExtra("userId", 1);

        //Populate ingredient recycler
        Repository repo = new Repository(getApplication());
        List<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
        for (Ingredient i : repo.getAllIngredients()) {
            if (i.getRecipeId() == recipeId) {
                recipeIngredients.add(i);
            }
        }
        final IngredientRecipeViewAdapter ingredientViewAdapter = new IngredientRecipeViewAdapter(this, getApplication());
        ingredients.setAdapter(ingredientViewAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientViewAdapter.setIngredients(recipeIngredients);


        Recipe current = repo.getRecipe(userId, recipeId);
        String name = getIntent().getStringExtra("name");
        int serves = getIntent().getIntExtra("ppl", 0);
        int estimate = getIntent().getIntExtra("estimate", 0);
        String createdBy = getIntent().getStringExtra("user");
        String instructionList = getIntent().getStringExtra("instructions");


        recipeName.setText(name);
        createdUser.setText("by " + createdBy);
        numServed.setText(serves+"");
        timeEst.setText(estimate+"");
        instruction.setText(instructionList);
        creationDate.setText(current.getDateCreated().toString());
        updated.setText(current.getLastUpdate().toString());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRecipe.this, EditRecipe.class);
                intent.putExtra("id", current.getId());
                intent.putExtra("userId", current.getUserId());
                intent.putExtra("name", current.getName());
                intent.putExtra("ppl", current.getPplServed());
                intent.putExtra("estimate", current.getTimeEstimate());
                intent.putExtra("user", current.getCreatedBy().getClass().getName());
                intent.putExtra("instructions", current.getInstructions());
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareRecipe(repo, name, createdBy, serves, estimate, instructionList);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void ShareRecipe(Repository repo, String name, String createdBy, int serves, int estimate, String instructionList) {
        String ingredientList = "";
        List<Ingredient> ingredients = repo.getAllIngredients();
        if (!ingredients.isEmpty()) {
            for (Ingredient i : ingredients) {
                ingredientList += i.getAmt() + " " + i.getMeasurement() + " " + i.getName() + "\n";
            }
        }

        String recipe = name + " — " + createdBy + "\nServings: " + serves + "\nTime Est.: " + estimate + " minutes" + "\nIngredients:\n" + ingredientList + "Instructions:\n" + instructionList;
        Intent msg = new Intent();
        msg.setAction(Intent.ACTION_SEND);
        msg.putExtra(Intent.EXTRA_TITLE, "Shared Recipe");
        msg.putExtra(Intent.EXTRA_TEXT, recipe);
        msg.setType("text/plain");
        Intent share = Intent.createChooser(msg, null);
        startActivity(share);
    }

    protected void onResume() {
        super.onResume();
        Repository repo = new Repository(getApplication());
        Recipe current = repo.getRecipe(userId, recipeId);
        TextView recipeName = findViewById(R.id.recipe_name);
        recipeName.setText(current.getName());
        TextView numServed = findViewById(R.id.recipePplEdit);
        numServed.setText(current.getPplServed()+"");
        TextView timeEst = findViewById(R.id.timeEdit);
        timeEst.setText(current.getTimeEstimate()+"");
        TextView instruction = findViewById(R.id.instructionEdit);
        instruction.setText(current.getInstructions());
        TextView updated = findViewById(R.id.lastUpdate);
        updated.setText(current.getLastUpdate().toString());
    }
}