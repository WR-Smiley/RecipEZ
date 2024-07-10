package com.smileydev.recipez.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
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
import java.time.Instant;
import java.util.Date;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_user);
        Button newUser = findViewById(R.id.addUser);
        EditText name = findViewById(R.id.nameEdit);
        EditText title = findViewById(R.id.titleEdit);
        EditText email = findViewById(R.id.emailEdit);
        EditText username = findViewById(R.id.usernameEdit);
        EditText password = findViewById(R.id.passwordEdit);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(name, title, email, username, password);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void createUser(EditText name, EditText title, EditText email, EditText username, EditText password) {
        Repository repo = new Repository(getApplication());
        if (username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() ||
                name.getText().toString().isEmpty() ||
                title.getText().toString().isEmpty() ||
                // Validate email is not empty and that it matches an email pattern
                (email.getText().toString().isEmpty()) || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            AlertDialog.Builder emptyAlert = new AlertDialog.Builder(NewUser.this);
            emptyAlert.setMessage("Please complete all fields. Ensure email is formatted correctly.");
            emptyAlert.setTitle("Empty Fields");
            emptyAlert.setCancelable(false);
            emptyAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog dialog = emptyAlert.create();
            dialog.show();
        }
        else if (password.getText().toString().contains("*") ||
                password.getText().toString().contains("DELETE") ||
                password.getText().toString().contains("FROM") ||
                password.getText().toString().contains("DROP") ||
                password.getText().toString().contains("WHERE") ||
                password.getText().toString().contains("/") ||
                password.getText().toString().contains("\\")) {
            AlertDialog.Builder passwordAlert = new AlertDialog.Builder(NewUser.this);
            passwordAlert.setMessage("Password may not contain '*', '/', '\\', or forbidden phrases");
            passwordAlert.setTitle("Password Forbidden");
            passwordAlert.setCancelable(false);
            passwordAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog dialog = passwordAlert.create();
            dialog.show();
        }
        else {
            Boolean userMatch = false;
            for (User u : repo.getAllUsers()) {
                if (username.getText().toString().equals(u.getUsername())) {
                    userMatch = true;
                }
            }
            if (!userMatch) {
                try {
                    // Get current time for creationDate and lastUpdate
                    Instant instant = Instant.now();
                    Date now = Date.from(instant);
                    // Create hashed password with 128-bit encryption
                    String encryptedPassword = null;
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    digest.update(password.getText().toString().getBytes());
                    byte[] bytes = digest.digest();
                    StringBuilder passwordBuilder = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        passwordBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    encryptedPassword = passwordBuilder.toString();
                    User newUser = new User(name.getText().toString(), now, now, title.getText().toString(), email.getText().toString(), username.getText().toString(), encryptedPassword);
                    repo.insert(newUser);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                AlertDialog.Builder usernameAlert = new AlertDialog.Builder(NewUser.this);
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
}