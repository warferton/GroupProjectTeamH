package com.teamh.teamhfinalproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.util.ArrayList;
import java.util.List;

public class GiftListPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftlist);

        //delete stupid ActionBar on the top of the screen
        try { this.getSupportActionBar().hide(); }
        catch (NullPointerException e){ e.printStackTrace();}

        List<String> tags = new ArrayList<String>();
        tags.add("collectible");
        tags.add("vintage");

        EtsyProduct gift1 = new EtsyProduct("228", "1947 farthing Coin With a Wren from the United kingdom, Perfect for Birthdays ,Anniversary and within Jewellery", "yo", tags, "https://www.etsy.com/listing/549266967/1947-farthing-coin-with-a-wren-from-the?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=&ref=sr_gallery-1-3", 2.15, "https://i.etsystatic.com/13022889/r/il/0aeb3d/1263449508/il_1588xN.1263449508_6dto.jpg");
        EtsyProduct gift2 = new EtsyProduct("229", "1864 Civil War CSA Confederate States of America 20 Bill Note Money", "uo", tags, "https://www.etsy.com/listing/253290233/1864-civil-war-csa-confederate-states-of?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=&ref=sr_gallery-1-4&cns=1", 89.95, "https://i.etsystatic.com/8000189/r/il/54ac6a/3036384383/il_1588xN.3036384383_lo8g.jpg");

        List<EtsyProduct> gifts = new ArrayList<>();
        gifts.add(gift1);
        gifts.add(gift2);


        GridView giftsGrid = (GridView) findViewById(R.id.giftsGridView);
        giftsGrid.setAdapter(new GiftsAdapter(this, gifts));
    }
}