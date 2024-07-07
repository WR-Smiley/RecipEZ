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

import java.security.MessageDigest;

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
                try {
                    // Create boolean to allow for either logging in or showing error message.
                    Boolean logged_in = false;
                    User login_user = null;
                    // Hash password
                    String encryptedPassword = null;
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    digest.update(password.getText().toString().getBytes());
                    byte[] bytes = digest.digest();
                    StringBuilder passwordBuilder = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        passwordBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    encryptedPassword = passwordBuilder.toString();
                    // Compare username and hashed password to database
                    Repository repo = new Repository(getApplication());
                    for (User u : repo.getAllUsers()) {
                        if (username.getText().toString().equals(u.getUsername()) && (encryptedPassword.equals(u.getPassword()))) {
                            logged_in = true;
                            login_user = u;
                        }
                    }
                    if (logged_in) {
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        intent.putExtra("userId", login_user.getId());
                        intent.putExtra("userName", login_user.getName());
                        startActivity(intent);
                        finish();
                    }
                    else {
                        AlertDialog.Builder loginAlert = new AlertDialog.Builder(MainActivity.this);
                        loginAlert.setMessage("Username or password incorrect.");
                        loginAlert.setTitle("Invalid login");
                        loginAlert.setCancelable(false);
                        loginAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                        });
                        AlertDialog dialog = loginAlert.create();
                        dialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewUser.class);
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