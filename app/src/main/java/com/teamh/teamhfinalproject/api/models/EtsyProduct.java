package com.teamh.teamhfinalproject.api.models;

import android.media.Image;

import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

/**
 * Simple placeholder object to represent
 * retrieved products.
 *
 * Subject to change
 */
public class EtsyProduct {
    private String id;
    private String title;
    private String description;
    private List<String> tags;
    private URL url;
    private double price;
    private Image img;

    public EtsyProduct(String id,
                       String title,
                       String description,
                       List<String> tags,
                       URL url,
                       double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.url = url;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public URL getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }

    public Image getImg() {
        return img;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass() != EtsyProduct.class)
            return false;
        return ((EtsyProduct) obj).getId().equals(this.id);
    }
}
