package com.recipe.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.recipe.Data.RecipeRecyclerViewAdapter;
import com.recipe.Model.Recipe;
import com.recipe.R;
import com.recipe.Util.Constants;
import com.recipe.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    private List<Recipe> recipeList;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Prefs prefs = new Prefs(MainActivity.this);
        String search = prefs.getSearch();
        recipeList = new ArrayList<>();
       // getMovies(search);
        recipeList = getMovies(search);
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this, recipeList);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);
        recipeRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.new_search) {
            showInputDialog();
           // return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showInputDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_view, null);
        final EditText newSearchEdt = (EditText) view.findViewById(R.id.searchEdt);
        Button submitButton = (Button) view.findViewById(R.id.submitButton);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs prefs = new Prefs(MainActivity.this);
                if (!newSearchEdt.getText().toString().isEmpty()) {
                    String search = newSearchEdt.getText().toString();
                    prefs.setSearch(search);
                    recipeList.clear();
                    getMovies(search);
                    recipeRecyclerViewAdapter.notifyDataSetChanged();//Very important!!
                }
                dialog.dismiss();
            }
        });
    }

    //Get movies
    public List<Recipe> getMovies(String searchTerm) {
        recipeList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL_LEFT + searchTerm, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("Search");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject recipeObj = jsonArray.getJSONObject(i);
                        Recipe recipe = new Recipe();
                        recipe.setName(recipeObj.getString("name"));
                        recipe.setTotalTime("Total Time: " + recipeObj.getString("totalTime"));
                        recipe.setTotalCal("Total Calories: " + recipeObj.getDouble("totalCal"));
                        recipe.setFat_kcal(recipeObj.getString("fat_kcal"));
                        recipe.setEnerc_fat(recipeObj.getString("enerc_fat"));
                        recipe.setSource_url(recipeObj.getString("source_url"));
                        recipe.setCourse(recipeObj.getString("course"));
                        recipe.setCuisine(recipeObj.getString("cuisine"));
                        recipe.setPhoto(recipeObj.getString("photo"));
                        recipeList.add(recipe);
                    }
                    /**
                     * Very important!! Otherwise, we wont see anything being displayed.
                     */
                    recipeRecyclerViewAdapter.notifyDataSetChanged();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR: ", error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
        return recipeList;
    }
}
