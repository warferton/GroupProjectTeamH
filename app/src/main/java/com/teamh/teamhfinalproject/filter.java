package com.teamh.teamhfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        try { this.getSupportActionBar().hide(); } catch (NullPointerException e){}
    }
}