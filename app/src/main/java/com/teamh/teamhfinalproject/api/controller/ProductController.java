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
import java.util.Arrays;
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
                API_URL.getUri() + "listings/active" + API_KEY.getUri(),
                null,
                response ->{
                    try {
                        JSONArray product_array = response.getJSONArray("results");
                        List<EtsyProduct> products = new ArrayList<>();
                        JSONArray tags_array;
                        for(int i=0; i < product_array.length(); i++){
                            try {
                                tags_array = product_array.getJSONObject(i).getJSONArray("tags");
                            }catch (Exception e){
                                tags_array = new JSONArray();
                                Log.e("Response Processing", "Object has no 'tags' property.");
                            }
                            products.add(
                                    JsonParser.parseProduct(product_array.getJSONObject(i).toString(),
                                            tags_array)
                            );
                        }
                        Log.i("Success | Products got", products.size() + "");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                },
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

    public JsonObjectRequest getListingById(String listing_id){
        return new JsonObjectRequest(
                Request.Method.GET,
                API_URL.getUri() + "listings/" + listing_id + API_KEY.getUri(),
                null,
                response -> {

                    try {
                        JSONArray product_array = response.getJSONArray("results");
                        List<EtsyProduct> products = new ArrayList<>();
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

    public JsonObjectRequest getListingImages(String listing_id){
        return new JsonObjectRequest(
                Request.Method.GET,
                API_URL.getUri() + "listings/" + listing_id + "/images" + API_KEY.getUri(),
                null,
                response -> {

                    try {
                        JSONArray images_array = response.getJSONArray("results");
                        List<String[]> images = new ArrayList<>();
                        for(int i=0; i < images_array.length(); i++){
                            images.add(
                                    JsonParser.parseProductImages(images_array.getJSONObject(i).toString())
                            );
                        }
                        Log.i("Success", Arrays.toString(images.get(0)));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Log.d("Error", "Failed requesting Images for product. Product ID: " + listing_id);
                    }

                },
                error -> Log.d("restapi", error.toString())
        );
    }


}
