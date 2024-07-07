package com.smileydev.recipez.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_recipe);
        Button saveButton = findViewById(R.id.save);
        EditText recipeName = findViewById(R.id.recipe_name);
        TextView createdUser = findViewById(R.id.creator);
        EditText numServed = findViewById(R.id.recipePplEdit);
        EditText timeEst = findViewById(R.id.timeEdit);
        EditText instruction = findViewById(R.id.instructionEdit);
        TextView creationDate = findViewById(R.id.dateCreated);
        TextView updated = findViewById(R.id.lastUpdate);
        RecyclerView ingredients = findViewById(R.id.ingredientRecycler);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recipeName.getText().toString().isEmpty() ||
                numServed.getText().toString().isEmpty()) {
                    ;
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