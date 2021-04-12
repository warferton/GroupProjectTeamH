package com.teamh.teamhfinalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        String category = getIntent().getStringExtra("category");
        category = category.substring(0, category.length() - 3);

        resultArray = productService.selectByDescription(category);

        if (resultArray.isEmpty()) {
            resultArray = productService.selectByTag(category);
        }
        if (resultArray.isEmpty()) {
            resultArray = productService.selectByTitle(category);
        }

        if (resultArray.isEmpty()) {
            Log.d("banana", "is empty");
            Toast.makeText(getApplicationContext(),"Please select another category", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Please select another category", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Button button = findViewById(R.id.LoadingPageButton);
            button.setOnClickListener(v -> RunGiftListPage());
        }
        Log.d("banana", String.valueOf(resultArray.isEmpty()));
        Log.d("banana2", resultArray.toString());

        //Example line
        //Log.i("ALL PRODUCTS IN DB", productService.getAll().toString());
    }


    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);
        intent.putExtra("object", resultArray.get(0).toString());
        startActivity(intent);
    }
}