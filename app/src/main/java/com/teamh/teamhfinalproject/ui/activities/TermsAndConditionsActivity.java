package com.teamh.teamhfinalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamh.teamhfinalproject.MainActivity;
import com.teamh.teamhfinalproject.R;

public class TermsAndConditionsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                    openMainActivity();
                }
        });
        try { this.getSupportActionBar().hide(); }
        catch (NullPointerException e){ e.printStackTrace();}
    }



    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
