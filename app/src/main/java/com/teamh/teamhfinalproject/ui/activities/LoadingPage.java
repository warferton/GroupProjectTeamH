package com.teamh.teamhfinalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;
import com.teamh.teamhfinalproject.api.service.ProductService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoadingPage extends AppCompatActivity {

    private ProductService productService;

    @Inject
    public LoadingPage(ProductService productService){
        this.productService = productService;
    }

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
    }

    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);

        startActivity(intent);
    }
}