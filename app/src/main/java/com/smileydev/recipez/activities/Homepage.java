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

import com.smileydev.recipez.R;

public class Homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Button newRecipe = findViewById(R.id.addRecipe);
        TextView welcome = findViewById(R.id.welcomeMsg);
        int userId = getIntent().getIntExtra("userId", -1);
        String userName = getIntent().getStringExtra("userName");
        welcome.setText("Welcome to RecipEZ, " + userName + "!");


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
}
