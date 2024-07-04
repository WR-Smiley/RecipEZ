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
import com.smileydev.recipez.activities.AddRecipe;
import com.smileydev.recipez.entities.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> allRecipes;

    private final Context context;

    private final LayoutInflater inflater;

    public RecipeAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeItem;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeItem = itemView.findViewById(R.id.recipeItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Recipe selected = allRecipes.get(position);

                    Intent intent = new Intent(context, AddRecipe.class);
                    intent.putExtra("id", selected.getId());
                    intent.putExtra("name", selected.getName());
                    intent.putExtra("create", selected.getDateCreated());
                    intent.putExtra("updated", selected.getLastUpdate());
                    intent.putExtra("ppl", selected.getPplServed());
                    intent.putExtra("estimate", selected.getTimeEstimate());
                    intent.putExtra("user", selected.getCreatedBy().getClass().getName());
                    intent.putExtra("instructions", selected.getInstructions());
                    intent.putExtra("ingredients", selected.getIngredients());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        if (allRecipes != null) {
            Recipe selected = allRecipes.get(position);
            String name = selected.getName();
            holder.recipeItem.setText(name);
        } else {
            holder.recipeItem.setText("No Recipes Yet!");
        }
    }

    @Override
    public int getItemCount() {
        if (allRecipes != null) {
            return allRecipes.size();
        }
        else return 0;
    }

    public void setRecipes(List<Recipe> recipes) {
        allRecipes = recipes;
        notifyDataSetChanged();
    }
}
