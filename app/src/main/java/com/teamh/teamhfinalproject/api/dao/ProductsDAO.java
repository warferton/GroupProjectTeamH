package com.teamh.teamhfinalproject.api.dao;

import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.util.List;

//Data Accessor Interface
public interface ProductsDAO {

//    List<EtsyProduct> selectByPrice(String price);

    List<EtsyProduct> getAll();

    List<EtsyProduct> selectByTag(String product_tag);

    List<EtsyProduct> selectByTitle(String product_title);

    List<EtsyProduct> selectByDescription(String product_description);

    void add(EtsyProduct product);

    int deleteById(String product_id);

}
