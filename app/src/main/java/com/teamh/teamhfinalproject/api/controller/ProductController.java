package com.teamh.teamhfinalproject.api.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teamh.teamhfinalproject.api.models.JsonParser;
import com.teamh.teamhfinalproject.api.service.ProductService;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static com.teamh.teamhfinalproject.api.ConnectionDetails.API_KEY;
import static com.teamh.teamhfinalproject.api.ConnectionDetails.API_URL;

//TODO Need to extra modify maybe
public class ProductController {

    @Inject
    public ProductController(){}

    @Inject
    ProductService productService;

    public ProductService getService(){
        return productService;
    }

    public JsonObjectRequest getActiveListings(long limit, double max_price, String keyword) {
        return new JsonObjectRequest(
//                https://openapi.etsy.com/v2/listings/active?api_key=fe9ajqvj4nsrgj2p7x5lnlc0&limit=1000&max_price=500&keywords=man
                Request.Method.GET,
                API_URL.getUri() + "listings/active" + API_KEY.getUri() + "&limit=" + limit +
                 "&max_price=" + max_price + "&keywords=" + keyword,
                null,
                response ->{
                    try {
                        JSONArray product_array = response.getJSONArray("results");
                        JSONArray tags_array;
                        for(int i=0; i < product_array.length(); i++){
                            try {
                                tags_array = product_array.getJSONObject(i).getJSONArray("tags");
                            }catch (Exception e){
                                tags_array = new JSONArray();
                                Log.e("Response Processing", "Object has no 'tags' property.");
                            }
                            productService.add(
                                    JsonParser.parseProduct(product_array.getJSONObject(i).toString(),
                                            tags_array)
                            );
                        }
                        Log.i("Success | Products got", productService.getAll().size() + "");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                },
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
                        for(int i=0; i < product_array.length(); i++){
                            productService.add(
                                    JsonParser.parseProduct(product_array.getJSONObject(i).toString(),
                                    product_array.getJSONObject(i).getJSONArray("tags"))
                            );
                        }
                        Log.i("Success", productService.toString());
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
