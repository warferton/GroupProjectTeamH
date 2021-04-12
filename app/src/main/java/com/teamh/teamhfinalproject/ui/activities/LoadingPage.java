package com.teamh.teamhfinalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.controller.ProductController;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;
import com.teamh.teamhfinalproject.api.service.ProductService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoadingPage extends AppCompatActivity {

    List<EtsyProduct> resultArray;

    @Inject
    public LoadingPage(){}

    private RequestQueue request;

    @Inject
    ProductController pc;
    ProductService productService;

    private String category;
    double max_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        productService = pc.getService();
        category = getIntent().getStringExtra("category");
        category = category.substring(0, category.length() - 3);
        max_price = Double.parseDouble(getIntent().getStringExtra("maxPrice"));

        RunRequest();
    }

    public void RunRequest(){
        request = Volley.newRequestQueue(this);
        request.add(pc.getActiveListings(75, max_price, category)); // 100 is max


        resultArray = productService.selectByDescription(category);

        if (resultArray.isEmpty()) {
            resultArray = productService.selectByTag(category);
        }
        if (resultArray.isEmpty()) {
            resultArray = productService.selectByTitle(category);
        }

        if (resultArray.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Oops. Sorry, We Couldn't Find you anything.\n Please select another category", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Button button = findViewById(R.id.LoadingPageButton);
            button.setOnClickListener(v -> RunGiftListPage());
        }
    }

    public void RunGiftListPage()
    {
        Intent intent = new Intent(this, GiftListPageActivity.class);
        //intent.putExtra("object", resultArray.get(0).toString());
        for(int i = 0; i < resultArray.size() ; i++)
        {
            intent.putExtra("object"+ i, resultArray.get(i).toString());
        }
        startActivity(intent);
    }
}