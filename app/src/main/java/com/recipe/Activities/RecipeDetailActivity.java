package com.recipe.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.recipe.Model.Recipe;
import com.recipe.R;
import com.recipe.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeDetailActivity extends AppCompatActivity {

    private Recipe recipe;
    private TextView name;
    private ImageView image;
    private TextView course;
    private TextView cuisine;
    private TextView totalTime;
    private TextView totalCal;
    private TextView ingredients;
    private TextView sourceLink;

    //private RequestQueue queue;
    //private long recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        //queue = Volley.newRequestQueue(this);
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        //recipeId = recipe.getId();
        setUpUI();
        getRecipeDetails();
    }

    private void getRecipeDetails() {
        name.setText(recipe.getName());
        course.setText("Course: "+recipe.getCourse());
        cuisine.setText("Cuisine: "+recipe.getCuisine());
        totalTime.setText(recipe.getTotalTime());
        totalCal.setText(recipe.getTotalCal());
        String[] ingredients=recipe.getIngredients();
        String ingredient="Ingrediens:\n";
        for(int i=0;i<ingredients.length;i++)
            ingredient+=ingredients[i]+"\n";
        this.ingredients.setText(ingredient);
        sourceLink.setText("Source Link:\n"+recipe.getSource_url());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                Constants.URL + id+Constants.API_KEY, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try{
//                    if (response.has("Ratings")) {
//                        JSONArray ratings = response.getJSONArray("Ratings");
//                        String source = null;
//                        String value = null;
//                        if (ratings.length() > 0) {
//                            JSONObject mRatings = ratings.getJSONObject(ratings.length() - 1);
//                            source = mRatings.getString("Source");
//                            value = mRatings.getString("Value");
//                            totalCal.setText(source + " : " + value);
//                        }else {
//                            totalCal.setText("Ratings: N/A");
//                        }
//                        name.setText(response.getString("Title"));
//                        totalTime.setText("Released: " + response.getString("Released"));
//                        Picasso.with(getApplicationContext())
//                                .load(response.getString("Poster"))
//                                .into(image);
//                    }
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Error:", error.getMessage());
//
//            }
//        });
//        queue.add(jsonObjectRequest);
    }

    private void setUpUI() {
        name = findViewById(R.id.recipeNameIDDets);
        image = findViewById(R.id.recipeImageIDDets);
        course = findViewById(R.id.courseIDDets);
        cuisine = findViewById(R.id.recipeCuisineIDDet);
        totalTime = findViewById(R.id.recipeTimeIDDets);
        totalCal = findViewById(R.id.totalCalIDDet);
        ingredients = findViewById(R.id.ingredientDet);
        sourceLink = findViewById(R.id.sourceLinkDet);
    }
}
