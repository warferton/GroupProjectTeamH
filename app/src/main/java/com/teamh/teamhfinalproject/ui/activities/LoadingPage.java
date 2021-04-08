package com.teamh.teamhfinalproject.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.dao.ProductDataAccess;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        int price = getIntent().getIntExtra("price", 5);
        String category = getIntent().getStringExtra("category");
        category = category.substring(0,category.length()-3);

        List<EtsyProduct> resultArray;
        ProductDataAccess DA = new ProductDataAccess();
        resultArray = DA.selectByDescription(category);
        Log.d("banana", resultArray.toString());
        if(resultArray.isEmpty())
        {
            resultArray = DA.selectByTag(category);
            Log.d("banana1", resultArray.toString());
        }
        if(resultArray.isEmpty())
        {
            resultArray = DA.selectByTitle(category);
        }
        Log.d("banana", resultArray.toString());
    }

    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);

        startActivity(intent);
    }
}