package com.smileydev.recipez.services;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smileydev.recipez.R;
import com.smileydev.recipez.activities.ViewRecipe;
import com.smileydev.recipez.dao.database.Repository;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> allRecipes;

    private final Context context;

    private final Application application;

    private final LayoutInflater inflater;

    public RecipeAdapter(Context context, Application application) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.application = application;
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
                    Repository repo = new Repository(application);
                    User u = repo.getUser(selected.getUserId());


                    Intent intent = new Intent(context, ViewRecipe.class);
                    intent.putExtra("id", selected.getId());
                    intent.putExtra("userId", selected.getUserId());
                    intent.putExtra("name", selected.getName());
                    intent.putExtra("create", selected.getDateCreated());
                    intent.putExtra("updated", selected.getLastUpdate());
                    intent.putExtra("ppl", selected.getPplServed());
                    intent.putExtra("estimate", selected.getTimeEstimate());
                    intent.putExtra("user", u.getName());
                    intent.putExtra("instructions", selected.getInstructions());
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
