package com.teamh.teamhfinalproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamh.teamhfinalproject.R;

public class LoadingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        try { this.getSupportActionBar().hide(); }
        catch (NullPointerException e){ e.printStackTrace();}
        Button button = findViewById(R.id.LoadingPageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunGiftListPage();
            }
        });

    }

    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);
        startActivity(intent);
    }
}