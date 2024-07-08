package com.smileydev.recipez.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.smileydev.recipez.R;
import com.smileydev.recipez.dao.database.Repository;
import com.smileydev.recipez.entities.Ingredient;

import java.time.Instant;
import java.util.Date;

public class AddIngredient extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner measurement;
    String[] volumeMeasurements;
    String[] weightMeasurements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ingredient);

        measurement = findViewById(R.id.measurementSelect);
        weightMeasurements = getResources().getStringArray(R.array.weightMeasurements);
        volumeMeasurements = getResources().getStringArray(R.array.volumeMeasurements);
        Button saveButton = findViewById(R.id.save);
        EditText ingredientName = findViewById(R.id.ingredientName);
        EditText amount = findViewById(R.id.ingredientAmtEdit);
        Instant instant = Instant.now();
        Date now = Date.from(instant);

        // Get passed info from intent
        int recipeId = getIntent().getIntExtra("recipeId", -1);

        // Set up Spinners
        String[] measurementTypes = getResources().getStringArray(R.array.measurementTypes);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, measurementTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        Spinner measurementType = findViewById(R.id.measurementTypeSelect);
        measurementType.setOnItemSelectedListener(this);
        measurementType.setAdapter(typeAdapter);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amt = -1;
                try {
                    amt = Integer.parseInt(amount.getText().toString());
                } catch (Exception e) {
                    AlertDialog.Builder intAlert = new AlertDialog.Builder(AddIngredient.this);
                    intAlert.setMessage("Amount must be a positive integer.");
                    intAlert.setTitle("Forbidden Entry");
                    intAlert.setCancelable(false);
                    intAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = intAlert.create();
                    dialog.show();
                }
                if (ingredientName.getText().toString().isEmpty() || amount.getText().toString().isEmpty()) {
                    AlertDialog.Builder emptyAlert = new AlertDialog.Builder(AddIngredient.this);
                    emptyAlert.setMessage("Name and Amount must not be blank.");
                    emptyAlert.setTitle("Negative Numbers");
                    emptyAlert.setCancelable(false);
                    emptyAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = emptyAlert.create();
                    dialog.show();
                }
                else if (amt < 0) {
                    AlertDialog.Builder negativeAlert = new AlertDialog.Builder(AddIngredient.this);
                    negativeAlert.setMessage("Amount must be a positive integer.");
                    negativeAlert.setTitle("Forbidden Entry");
                    negativeAlert.setCancelable(false);
                    negativeAlert.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog dialog = negativeAlert.create();
                    dialog.show();
                }
                else {
                    Repository repo = new Repository(getApplication());
                    String measurementTypeText = measurementType.getSelectedItem().toString();
                    String measurementText = measurement.getSelectedItem().toString();
                    Ingredient ingredient = new Ingredient(ingredientName.getText().toString(), now, now, amt, measurementTypeText, measurementText, recipeId);
                    repo.insert(ingredient);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        measurement = findViewById(R.id.measurementSelect);

        if (parent.getId() == R.id.measurementTypeSelect) {
            String spinnerValue = parent.getItemAtPosition(position).toString();
            if (spinnerValue.equals("Weight")) {
                ArrayAdapter weightAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, weightMeasurements);
                weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                measurement.setAdapter(weightAdapter);
            }
            else if (spinnerValue.equals("Volume")) {
                ArrayAdapter volumeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, volumeMeasurements);
                volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                measurement.setAdapter(volumeAdapter);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
