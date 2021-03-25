package com.teamh.teamhfinalproject.api.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teamh.teamhfinalproject.api.service.ProductService;

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
                API_URL + "listings/active" + API_KEY, null,

                response -> {
                    String reply = response.toString();
                    String guess = "title";
                    int len = reply.indexOf(guess);
                    int len2 = reply.indexOf("description");
                    reply = reply.substring(len + 8, len2 - 3);

                },
                error -> Log.d("restapi", error.toString())
        );
    }
    public JsonObjectRequest getStoreInventory(String store_id) {
       return new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "listings/" + store_id + "/inventory" + API_KEY, null,
                response -> {
                    String reply = response.toString();
                    String guess = "long";
                    int len = reply.indexOf(guess);
                    //to get the exact "$23.42 US" format, change hardcoded 7 by 6
                    // and 16 by 13 if you want only the double
                    reply = reply.substring(len + 7, len + 16);
                    Log.d("restapi", reply);
                },
                error -> Log.d("restapi", error.toString())
        );
    }

    JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "taxonomy/buyer/get" + API_KEY, null,

                response -> {
                    String reply = response.toString();
                    String guess = "name";

                    Log.d("restapi", reply);
                },
                error -> Log.d("restapi", error.toString())
        );


}
