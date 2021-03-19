package com.teamh.teamhfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.teamh.teamhfinalproject.api.ebay.security.EbayTokenManager;
import com.teamh.teamhfinalproject.ui.activities.FilterPageActivity;
import com.teamh.teamhfinalproject.ui.activities.TermsAndConditionsActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    //instantiate token manager
    private EbayTokenManager tokenManager = new EbayTokenManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**load creds from yaml**/
        try {
            CredentialUtil.load(new FileInputStream("./api/ebay/ebay-config.yml"));
            System.out.println("\n=========Successfully Loaded the Credentials =========\n");
        } catch (FileNotFoundException e) {
            System.err.println("Failed to Load Credentials at MainActivity.java, 40.\n");
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.find_gift_button);
        fab.setOnClickListener(view -> openFilterActivity());
        Button terms_button = findViewById(R.id.nav_button_terms);
        terms_button.setOnClickListener(view -> openTermsAndConditionsActivity());
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