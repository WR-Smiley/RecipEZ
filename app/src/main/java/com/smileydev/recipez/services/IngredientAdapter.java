package com.smileydev.recipez.services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;
import com.smileydev.recipez.activities.EditIngredient;
import com.smileydev.recipez.entities.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> allIngredients;

    private final Context context;

    private final LayoutInflater inflater;

    public IngredientAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView ingredientName;
        private final TextView ingredientMeasurement;
        private final TextView ingredientAmount;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientMeasurement = itemView.findViewById(R.id.measurement);
            ingredientAmount = itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Ingredient selected = allIngredients.get(position);

                    Intent intent = new Intent(context, EditIngredient.class);
                    intent.putExtra("name", selected.getName());
                    intent.putExtra("create", selected.getDateCreated());
                    intent.putExtra("updated", selected.getLastUpdate());
                    intent.putExtra("amount", selected.getAmt());
                    intent.putExtra("measurementType", selected.getMeasurementType());
                    intent.putExtra("measurement", selected.getMeasurement());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder holder, int position) {
        if (allIngredients != null) {
            Ingredient selected = allIngredients.get(position);
            int amount = selected.getAmt();
            String measurement = selected.getMeasurement();
            String name = selected.getName();
            holder.ingredientAmount.setText(amount);
            holder.ingredientMeasurement.setText(measurement);
            holder.ingredientName.setText(name);
        } else {
            holder.ingredientName.setText("No Recipes Yet!");
        }
    }

    @Override
    public int getItemCount() {
        if (allIngredients != null) {
            return allIngredients.size();
        }
        else return 0;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        allIngredients = ingredients;
        notifyDataSetChanged();
    }
}
