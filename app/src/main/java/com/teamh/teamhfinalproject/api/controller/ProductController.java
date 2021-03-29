package com.teamh.teamhfinalproject.api.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;
import com.teamh.teamhfinalproject.api.models.JsonParser;
import com.teamh.teamhfinalproject.api.service.ProductService;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.teamh.teamhfinalproject.api.ConnectionDetails.API_KEY;
import static com.teamh.teamhfinalproject.api.ConnectionDetails.API_URL;

//TODO Need to extra modify maybe
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public JsonObjectRequest getActiveListings() {
        return new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "listings/active" + API_KEY,
                null,
                null,
                error -> Log.d("restapi", error.toString())
        );
    }
    
    public JsonObjectRequest getStoreInventory(String store_id) {
       return new JsonObjectRequest(
                Request.Method.GET,
                API_URL.getUri() + "listings/" + store_id + "/inventory" + API_KEY.getUri(),
               null,
               null,
                error -> Log.d("restapi", error.toString())
        );
    }

    public JsonObjectRequest getBuyerTaxonomy() {
        return new JsonObjectRequest(
                Request.Method.GET,
                API_URL.getUri() + "taxonomy/buyer/get" + API_KEY.getUri(),
                null,
                null,
                error -> Log.d("restapi", error.toString())
        );
    }

    public JsonObjectRequest getListingById(String listing_id){
        return new JsonObjectRequest(
                Request.Method.GET,
                API_URL.getUri() + "listings/" + listing_id + API_KEY.getUri(),
                null,
                response -> {
                    //TODO: Constructor Doesn't work on response yet need further config

                    try {
                        JSONArray product_array = response.getJSONArray("results");
                        List<EtsyProduct> products = new ArrayList<EtsyProduct>();
                        List<String> tags = new ArrayList<>();
                        for(int i=0; i < product_array.length(); i++){
                            products.add(
                                    JsonParser.parseProduct(product_array.getJSONObject(i).toString(),
                                    product_array.getJSONObject(i).getJSONArray("tags"))
                            );
                        }
                        Log.i("Success", products.toString());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Log.d("restapi", error.toString())
        );
    }


}
