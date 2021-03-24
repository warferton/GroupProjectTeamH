package com.teamh.teamhfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamh.teamhfinalproject.ui.ObjectExample;
import com.teamh.teamhfinalproject.ui.activities.FilterPageActivity;
import com.teamh.teamhfinalproject.ui.activities.TermsAndConditionsActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String standardUrl = "https://openapi.etsy.com/v2/listings/active?api_key=fe9ajqvj4nsrgj2p7x5lnlc0";

        //this is the main part of the url to send the request
        String mainUrl = "https://openapi.etsy.com/v2/";

        //this is the api key that is set at the end of the request
        String apiKey = "?api_key=fe9ajqvj4nsrgj2p7x5lnlc0";

        String test = "667908964";
        Button btn = findViewById(R.id.trybutton);

        RequestQueue requestName = Volley.newRequestQueue(this);
        Log.d("banana", "Part2");
        JsonObjectRequest  objectRequest1 = new JsonObjectRequest(
                Request.Method.GET,
                 mainUrl + "listings/" + test + apiKey, null ,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String reply = response.toString();
                        String guess = "title";
                        int len = reply.indexOf(guess);
                        int len2 = reply.indexOf("description");
                        reply = reply.substring(len +8,len2 - 3);

                        Log.d("restapi", reply);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("restapi", error.toString());
                    }
                }
        );
        Log.d("restapi", "Done");

        requestName.add(objectRequest1);

        RequestQueue requestPrice = Volley.newRequestQueue(this);
        Log.d("banana", "Part2");
        JsonObjectRequest  objectRequest2 = new JsonObjectRequest(
                Request.Method.GET,
                mainUrl + "listings/" + test + "/inventory" + apiKey, null ,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String reply = response.toString();
                        String guess = "long";
                        int len = reply.indexOf(guess);
                        //to get the exact "$23.42 US" format, change hardcoded 7 by 6
                        // and 16 by 13 if you want only the double
                        reply = reply.substring(len + 7 ,len + 16);
                        Log.d("restapi", reply);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("restapi", error.toString());
                    }
                }
        );
        Log.d("restapi", "Done");

        requestPrice.add(objectRequest2);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.find_gift_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilterActivity();
            }
        });
        Button terms_button = findViewById(R.id.nav_button_terms);
        terms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTermsAndConditionsActivity();
            }
        });
        Log.d("bananas", "Hello2.5.3");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Log.d("bananas", "Hello5");

    }

    public void  openFilterActivity() {
        Intent intent = new Intent(this, FilterPageActivity.class);
        startActivity(intent);
    }

    public void openTermsAndConditionsActivity(){
        Intent intent = new Intent(this, TermsAndConditionsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}