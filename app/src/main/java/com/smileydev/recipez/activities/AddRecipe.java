package com.smileydev.recipez.activities;

import android.content.DialogInterface;
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
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;
import com.smileydev.recipez.dao.database.Repository;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import java.time.Instant;
import java.util.Date;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_recipe);
        Repository repo = new Repository(getApplication());
        Button saveButton = findViewById(R.id.save);
        EditText recipeName = findViewById(R.id.recipe_name);
        TextView createdUser = findViewById(R.id.creator);
        EditText numServed = findViewById(R.id.recipePplEdit);
        EditText timeEst = findViewById(R.id.timeEdit);
        EditText instruction = findViewById(R.id.instructionEdit);
        TextView creationDate = findViewById(R.id.dateCreated);
        TextView updated = findViewById(R.id.lastUpdate);
        RecyclerView ingredients = findViewById(R.id.ingredientRecycler);
        String userName = getIntent().getStringExtra("userName");
        int userId = getIntent().getIntExtra("userId", -1);
        User currentUser = repo.getUser(userId);
        Instant instant = Instant.now();
        Date now = Date.from(instant);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int servings = -1;
                int minutes = -1;
                try {
                    servings = Integer.parseInt(numServed.getText().toString());
                    minutes = Integer.parseInt(timeEst.getText().toString());
                    if (servings < 0 || minutes < 0) {
                        AlertDialog.Builder negativeAlert = new AlertDialog.Builder(AddRecipe.this);
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
                    AlertDialog.Builder intAlert = new AlertDialog.Builder(AddRecipe.this);
                    intAlert.setMessage("Servings and Time Estimate must only include numbers.");
                    intAlert.setTitle("Faulty Data Type");
                    intAlert.setCancelable(false);
                    intAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = intAlert.create();
                    dialog.show();
                }

                if (recipeName.getText().toString().isEmpty() ||
                numServed.getText().toString().isEmpty() ||
                timeEst.getText().toString().isEmpty() ||
                instruction.getText().toString().isEmpty() ||
                ingredients.getChildCount() == 0) {
                    AlertDialog.Builder emptyAlert = new AlertDialog.Builder(AddRecipe.this);
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
                    Recipe recipe = new Recipe(recipeName.getText().toString(), now, now, servings, minutes, currentUser, userId, instruction.getText().toString());
                    repo.insert(recipe);
                    Toast.makeText(AddRecipe.this, "Recipe added!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}