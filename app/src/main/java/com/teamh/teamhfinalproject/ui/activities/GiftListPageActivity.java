package com.teamh.teamhfinalproject.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamh.teamhfinalproject.R;

public class GiftListPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftlist);

        //delete stupid ActionBar on the top of the screen
        try { this.getSupportActionBar().hide(); }
        catch (NullPointerException e){ e.printStackTrace();}

    }


}