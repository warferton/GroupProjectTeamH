package com.teamh.teamhfinalproject.api.models;


import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

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
    private String url;
    private double price;
    private String img_url;

    public EtsyProduct(String id,
                       String title,
                       String description,
                       List<String> tags,
                       String url,
                       double price,
                       String img_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.url = url;
        this.price = price;
        this.img_url = img_url;
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

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return img_url;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass() != EtsyProduct.class)
            return false;
        return ((EtsyProduct) obj).getId().equals(this.id);
    }

    public void setTags(List<String> tags){
        this.tags = tags;
    }


    @NotNull
    @Override
    public String toString() {
        return "EtsyProduct{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", url=" + url +
                ", price=" + price +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
