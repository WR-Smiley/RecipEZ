package com.smileydev.recipez.activities;

import android.content.DialogInterface;
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
        Date now = new Date();

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository repo = new Repository(getApplication());
                for (User u : repo.getAllUsers()) {
                    if (username.getText().toString().equals(u.getUsername())) {
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
                if (username.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() ||
                name.getText().toString().isEmpty() ||
                title.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty()) {
                    AlertDialog.Builder emptyAlert = new AlertDialog.Builder(NewUser.this);
                    emptyAlert.setMessage("Please provide a username and password.");
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
                        password.getText().toString().contains("/") ||
                        password.getText().toString().contains("\\")) {
                    AlertDialog.Builder passwordAlert = new AlertDialog.Builder(NewUser.this);
                    passwordAlert.setMessage("Password contains forbidden characters.");
                    passwordAlert.setTitle("Password Forbidden");
                    passwordAlert.setCancelable(false);
                    passwordAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = passwordAlert.create();
                    dialog.show();
                }
                else {
                    try {
                        User newUser = new User(name.getText().toString(), now, now, title.getText().toString(), email.getText().toString(), username.getText().toString(), password.getText().toString());
                        repo.insert(newUser);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
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