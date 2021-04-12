package com.teamh.teamhfinalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;
import com.teamh.teamhfinalproject.api.service.ProductService;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoadingPage extends AppCompatActivity {

    List<EtsyProduct> resultArray;
    public LoadingPage(){}

    @Inject
    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        Log.d("banana", resultArray.toString());

        try { this.getSupportActionBar().hide(); }
        catch (NullPointerException e){ e.printStackTrace();}
        Button button = findViewById(R.id.LoadingPageButton);

        button.setOnClickListener(v -> RunGiftListPage());
        int price = getIntent().getIntExtra("price", 5);

        String category = getIntent().getStringExtra("category");
        category = category.substring(0,category.length()-3);

        resultArray = productService.selectByDescription(category);
        Log.d("banana", resultArray.toString());
        if(resultArray.isEmpty())
        {
            resultArray = productService.selectByTag(category);
            Log.d("banana1", resultArray.toString());
        }
        if(resultArray.isEmpty())
        {
            resultArray = productService.selectByTitle(category);
        }
        Log.d("banana", resultArray.toString());

        //Example line
        Log.i("ALL PRODUCTS IN DB", productService.getAll().toString());
    }


    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);
        //intent.putExtra("object", (Serializable) resultArray.get(0));
        startActivity(intent);
    }
}