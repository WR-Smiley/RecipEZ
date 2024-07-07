package com.smileydev.recipez.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.smileydev.recipez.R;
import com.smileydev.recipez.dao.database.Repository;
import com.smileydev.recipez.entities.User;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.loginButton);
        Button newUser = findViewById(R.id.newUser);
        EditText username = findViewById(R.id.usernameEntry);
        EditText password = findViewById(R.id.passwordEntry);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);
            }
        });



        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository repo = new Repository(getApplication());
                if (username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty()) {
                    AlertDialog.Builder emptyAlert = new AlertDialog.Builder(MainActivity.this);
                    emptyAlert.setMessage("Please provide a username and password.");
                    emptyAlert.setTitle("Empty Fields");
                    emptyAlert.setCancelable(false);
                    emptyAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = emptyAlert.create();
                    dialog.show();
                }
                for (User u : repo.getAllUsers()) {
                    if (username.getText().toString().equals(u.getUsername())) {
                        AlertDialog.Builder usernameAlert = new AlertDialog.Builder(MainActivity.this);
                        usernameAlert.setMessage("Username already taken, please choose another.");
                        usernameAlert.setTitle("Username taken");
                        usernameAlert.setCancelable(false);
                        usernameAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                        });
                        AlertDialog dialog = usernameAlert.create();
                        dialog.show();
                    }
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