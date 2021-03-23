package com.teamh.teamhfinalproject.api.models;

import android.media.Image;

/**
 * Simple placeholder object to represent
 * retrieved products.
 *
 * Subject to change
 */
public class EbayProduct {
    private String id;
    private String name;
    private double price;
    private Image img;

    public EbayProduct(String id, String name, double price, Image img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Image getImg() {
        return img;
    }
}
