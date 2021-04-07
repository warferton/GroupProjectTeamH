package com.teamh.teamhfinalproject.api.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Receives a JSON string of an object and a JSONArray for
     * a tag sub-array as its parameters, and parses it
     * to an instance of the <b>EtsyProduct</b> class
     *
     * @param json_string full JSON object string
     * @param tag_array array object string within the main object string, containing product tags
     * @return EtsyProduct parsed product instance
     * @throws IOException possible JSON string misread
     * @throws JSONException problem with JSON string format
     */
    public static EtsyProduct parseProduct(String json_string, JSONArray tag_array) throws IOException, JSONException {
        JsonNode root = objectMapper.readTree(json_string);
        String id = root.path("listing_id").asText();
        String title = root.path("title").textValue();
        String description = root.path("description").textValue();
        String url = root.path("url").textValue();
        double price = root.path("price").asDouble(0);
        String image = root.path("image").textValue();
        List<String> tags = new ArrayList<>();
        //parse tags array
        for(int j=0; j < tag_array.length(); j++){
            try {
                tags.add((String) tag_array.get(j));
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        return new EtsyProduct(id, title, description, tags, url, price, image);
    }

    /**
     * Parses a JSONObject response from product images request
     *
     * @param json_string full JSON object string
     * @return String[] string array containing 4 urls to images of four according sizes
     * @throws IOException possible JSON string misread
     */
    public static String[] parseProductImages(String json_string) throws IOException{

        JsonNode root = objectMapper.readTree(json_string);
        String small = root.path("url_75x75").asText();
        String medium = root.path("url_170x135").asText();
        String large = root.path("url_570xN").asText();
        String full_size = root.path("url_fullxfull").asText();
        System.err.println(small);
        return new String[] {small, medium, large, full_size};
    }
}
