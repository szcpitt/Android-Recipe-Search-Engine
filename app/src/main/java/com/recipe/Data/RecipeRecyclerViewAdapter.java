package com.recipe.Data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.Activities.RecipeDetailActivity;
import com.recipe.Model.Recipe;
import com.recipe.R;
import com.recipe.Util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Recipe> recipeList;

    public RecipeRecyclerViewAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        recipeList = recipes;
    }

    @Override
    public RecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerViewAdapter.ViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position);
        String posterLink = Constants.IMAGE_URL + recipe.getPhoto();
        holder.title.setText(recipe.getName());
        holder.cuisine.setText(recipe.getCuisine());
        Picasso.with(context)
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.photo);
        holder.calories.setText(recipe.getTotalCal());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView photo;
        TextView calories;
        TextView cuisine;

        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            title = itemView.findViewById(R.id.recipeNameID);
            photo = itemView.findViewById(R.id.recipeImageID);
            calories = itemView.findViewById(R.id.caloriesID);
            cuisine = itemView.findViewById(R.id.recipeCatID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Recipe recipe = recipeList.get(getAdapterPosition());
                    Intent intent = new Intent(context, RecipeDetailActivity.class);
                    intent.putExtra("recipe", recipe);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
