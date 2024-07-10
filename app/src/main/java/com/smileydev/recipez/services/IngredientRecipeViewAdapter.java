package com.smileydev.recipez.services;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;
import com.smileydev.recipez.entities.Ingredient;

import java.util.List;


public class IngredientRecipeViewAdapter extends RecyclerView.Adapter<IngredientRecipeViewAdapter.IngredientViewHolder> {

    private List<Ingredient> allIngredients;

    private final Context context;

    private final Application application;

    private final LayoutInflater inflater;

    public IngredientRecipeViewAdapter(Context context, Application application) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.application = application;
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
        }
    }

    @NonNull
    @Override
    public IngredientRecipeViewAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ingredient_item_no_delete, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientRecipeViewAdapter.IngredientViewHolder holder, int position) {
        if (allIngredients != null) {
            Ingredient selected = allIngredients.get(position);
            String amount = selected.getAmt();
            String measurement = selected.getMeasurement();
            String name = selected.getName();
            holder.ingredientAmount.setText(amount);
            holder.ingredientMeasurement.setText(measurement+"");
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
