package com.smileydev.recipez.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_recipe);
        Repository repo = new Repository(getApplication());
        Button save = findViewById(R.id.save);
        Button addIngredient = findViewById(R.id.addIngredientRecipe);
        Button refresh = findViewById(R.id.refreshView);

        // Get variable inits from Intent and update relevant text in activity view
        int recipeId = getIntent().getIntExtra("id", -1);
        int userId = getIntent().getIntExtra("userId", -1);
        Recipe current = repo.getRecipe(userId, recipeId);
        String recipeName = getIntent().getStringExtra("name");
        EditText recipeNameEdit = findViewById(R.id.recipe_name);
        recipeNameEdit.setText(recipeName);
        String createdBy = current.getCreatedBy().getName();
        TextView createdByEdit = findViewById(R.id.creator);
        createdByEdit.setText("by " + createdBy);
        int servings = getIntent().getIntExtra("ppl", -1);
        EditText recipePpl = findViewById(R.id.recipePplEdit);
        recipePpl.setText(servings+"");
        int estimate = getIntent().getIntExtra("estimate", -1);
        EditText timeEst = findViewById(R.id.timeEdit);
        timeEst.setText(estimate+"");
        String instructions = getIntent().getStringExtra("instructions");
        EditText instructionEdit = findViewById(R.id.instructionEdit);
        instructionEdit.setText(instructions);

        // Populate ingredient recyclerview
        RecyclerView ingredients = findViewById(R.id.ingredientRecycler);
        List<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
        for (Ingredient i : repo.getAllIngredients()) {
            if (i.getRecipeId() == recipeId) {
                recipeIngredients.add(i);
            }
        }
        final IngredientViewAdapter ingredientViewAdapter = new IngredientViewAdapter(this, getApplication());
        ingredients.setAdapter(ingredientViewAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientViewAdapter.setIngredients(recipeIngredients);

        // Get updated date to save to database
        Instant instant = Instant.now();
        Date now = Date.from(instant);

        // Set update listener and validate changes
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRecipe(current, now, recipePpl, timeEst, recipeNameEdit, instructionEdit, ingredients);
            }
        });

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRecipe.this, AddIngredient.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userName", current.getName());
                intent.putExtra("recipeId", recipeId);
                startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refresh();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Refresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void UpdateRecipe(Recipe current, Date now, EditText recipePpl, EditText timeEst, EditText recipeNameEdit, EditText instructionEdit, RecyclerView ingredients) {
        int servings = -1;
        int minutes = -1;
        try {
            servings = Integer.parseInt(recipePpl.getText().toString());
            minutes = Integer.parseInt(timeEst.getText().toString());
            if (servings < 0 || minutes < 0) {
                AlertDialog.Builder negativeAlert = new AlertDialog.Builder(EditRecipe.this);
                negativeAlert.setMessage("Servings and Time Estimate must be positive.");
                negativeAlert.setTitle("Negative Numbers");
                negativeAlert.setCancelable(false);
                negativeAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog dialog = negativeAlert.create();
                dialog.show();
            }
        } catch (Exception e) {
            AlertDialog.Builder intAlert = new AlertDialog.Builder(EditRecipe.this);
            intAlert.setMessage("Servings and Time Estimate must only include numbers.");
            intAlert.setTitle("Faulty Data Type");
            intAlert.setCancelable(false);
            intAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog dialog = intAlert.create();
            dialog.show();
        }

        if (recipeNameEdit.getText().toString().isEmpty() ||
                recipePpl.getText().toString().isEmpty() ||
                timeEst.getText().toString().isEmpty() ||
                instructionEdit.getText().toString().isEmpty() ||
                ingredients.getChildCount() == 0) {
            AlertDialog.Builder emptyAlert = new AlertDialog.Builder(EditRecipe.this);
            emptyAlert.setMessage("Please complete all fields! You must add at least one ingredient and instruction.");
            emptyAlert.setTitle("Empty Fields");
            emptyAlert.setCancelable(false);
            emptyAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog dialog = emptyAlert.create();
            dialog.show();
        }

        else {
            Repository repo = new Repository(getApplication());
            current.setName(recipeNameEdit.getText().toString());
            current.setPplServed(servings);
            current.setTimeEstimate(minutes);
            current.setLastUpdate(now);
            current.setInstructions(instructionEdit.getText().toString());
            repo.update(current);
            Toast.makeText(EditRecipe.this, "Changes saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        RecyclerView ingredientRecycler = findViewById(R.id.ingredientRecycler);
        Repository repo = new Repository(getApplication());
        int recipeId = getIntent().getIntExtra("id", -1);
        List<Ingredient> recipeIngredients = new ArrayList<Ingredient>();
        for (Ingredient i : repo.getAllIngredients()) {
            if (i.getRecipeId() == recipeId) {
                recipeIngredients.add(i);
            }
        }
        final IngredientViewAdapter ingredientViewAdapter = new IngredientViewAdapter(this, getApplication());
        ingredientRecycler.setAdapter(ingredientViewAdapter);
        ingredientRecycler.setLayoutManager(new LinearLayoutManager(this));
        ingredientViewAdapter.setIngredients(recipeIngredients);
    }
}
