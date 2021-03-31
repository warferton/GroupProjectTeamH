package com.teamh.teamhfinalproject.api.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class JsonParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static EtsyProduct parseProduct(String json_string, JSONArray tag_array) throws IOException, JSONException {
        JsonNode root = objectMapper.readTree(json_string);
        String id = root.path("listing_id").asText();
        String title = root.path("title").textValue();
        String description = root.path("description").textValue();
        String url = root.path("url").textValue();
        double price = root.path("price").asDouble(0);
        String image = root.path("image").textValue();
        List<String> tags = null;
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
}
