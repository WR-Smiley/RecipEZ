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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class RecipeSearchAdapter extends RecyclerView.Adapter<RecipeSearchAdapter.RecipeViewHolder> {

    private List<Recipe> allRecipes;

    private final Context context;

    private final Application application;

    private final LayoutInflater inflater;

    public RecipeSearchAdapter(Context context, Application application) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.application = application;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeName;
        private final TextView recipeCreator;
        private final TextView recipeCreated;
        private final TextView recipeUpdated;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            recipeCreator = itemView.findViewById(R.id.recipeCreator);
            recipeCreated = itemView.findViewById(R.id.recipeCreated);
            recipeUpdated = itemView.findViewById(R.id.recipeUpdated);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Recipe selected = allRecipes.get(position);
                    Repository repo = new Repository(application);
                    User u = repo.getUser(selected.getUserId());


                    Intent intent = new Intent(context, ViewRecipe.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
    public RecipeSearchAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recipe_result, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeSearchAdapter.RecipeViewHolder holder, int position) {
        if (allRecipes != null) {
            Recipe selected = allRecipes.get(position);
            String name = selected.getName();
            DateFormat parseFormat = new SimpleDateFormat( "yyyy-MM-dd");
            String creator = selected.getCreatedBy().getName();
            String created = parseFormat.format(selected.getDateCreated());
            String updated = parseFormat.format(selected.getLastUpdate());
            holder.recipeName.setText(name);
            holder.recipeCreator.setText(creator);
            holder.recipeCreated.setText(created);
            holder.recipeUpdated.setText(updated);
        } else {
            holder.recipeName.setText("No Recipes Yet!");
            holder.recipeCreator.setText(null);
            holder.recipeCreated.setText(null);
            holder.recipeUpdated.setText(null);
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
