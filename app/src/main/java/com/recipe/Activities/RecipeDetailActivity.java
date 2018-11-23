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
    private TextView movieYear;
    private TextView director;
    private TextView actors;
    private TextView cuisine;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;

    private RequestQueue queue;
    private long recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        queue = Volley.newRequestQueue(this);
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        recipeId = recipe.getId();
        setUpUI();
        getMovieDetails(recipeId);
    }

    private void getMovieDetails(long id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL + id+Constants.API_KEY, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if (response.has("Ratings")) {
                        JSONArray ratings = response.getJSONArray("Ratings");
                        String source = null;
                        String value = null;
                        if (ratings.length() > 0) {
                            JSONObject mRatings = ratings.getJSONObject(ratings.length() - 1);
                            source = mRatings.getString("Source");
                            value = mRatings.getString("Value");
                            rating.setText(source + " : " + value);
                        }else {
                            rating.setText("Ratings: N/A");
                        }
                        name.setText(response.getString("Title"));
                        movieYear.setText("Released: " + response.getString("Released"));
                        director.setText("Director: " + response.getString("Director"));
                        writers.setText("Writers: " + response.getString("Writer"));
                        plot.setText("Plot: " + response.getString("Plot"));
                        runtime.setText("Runtime: " + response.getString("Runtime"));
                        actors.setText("Actors: " + response.getString("Actors"));
                        Picasso.with(getApplicationContext())
                                .load(response.getString("Poster"))
                                .into(image);
                        boxOffice.setText("Box Office: " + response.getString("BoxOffice"));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:", error.getMessage());

            }
        });
        queue.add(jsonObjectRequest);
    }

    private void setUpUI() {
        name = findViewById(R.id.recipeNameIDDets);
        image = findViewById(R.id.recipeImageIDDets);
        movieYear = findViewById(R.id.recipeTimeIDDets);
        director = findViewById(R.id.directedByDet);
        cuisine = findViewById(R.id.recipeCuisineIDDet);
        rating = findViewById(R.id.movieRatingIDDet);
        writers = findViewById(R.id.writersDet);
        plot = findViewById(R.id.plotDet);
        boxOffice = findViewById(R.id.boxOfficeDet);
        runtime = findViewById(R.id.runtimeDet);
        actors = findViewById(R.id.actorsDet);
    }
}
