package com.teamh.teamhfinalproject.api.service;

import com.teamh.teamhfinalproject.api.dao.ProductsDAO;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.util.List;

import javax.inject.Inject;


public class ProductService {
    @Inject
    public ProductService(){}

    @Inject
    ProductsDAO productsDAO;

    public List<EtsyProduct> selectByTag(String product_tag){
        return productsDAO.selectByTag(product_tag);
    }

    public List<EtsyProduct> selectByTitle(String product_title){
        return productsDAO.selectByTitle(product_title);
    }

    public List<EtsyProduct> selectByDescription(String product_description){
        return productsDAO.selectByDescription(product_description);
    }

    public void add(EtsyProduct product){
        productsDAO.add(product);
    }

    public int deleteById(String product_id){
        return productsDAO.deleteById(product_id);
    }

    public List<EtsyProduct> getAll(){
        return productsDAO.getAll();
    }
}
