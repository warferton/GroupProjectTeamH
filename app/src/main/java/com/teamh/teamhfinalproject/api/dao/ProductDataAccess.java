package com.teamh.teamhfinalproject.api.dao;

import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;


//Local DB Repo
public class ProductDataAccess implements ProductsDAO, Serializable {
    private static List<EtsyProduct> productDB = new ArrayList<>();
    @Inject
    public ProductDataAccess() {}

    @Override
    public List<EtsyProduct> selectByTag(String product_tag) {
        return productDB.parallelStream()
                .filter(product -> product.getTags().contains(product_tag))
                .collect(Collectors.toList());
    }

    @Override
    public List<EtsyProduct> selectByTitle(String word) {
        return  productDB.parallelStream()
                .filter(product -> product.getTitle().contains(word))
                .collect(Collectors.toList());
    }

    @Override
    public List<EtsyProduct> selectByDescription(String word) {
        return productDB.parallelStream()
                .filter(product -> product.getDescription().contains(word))
                .collect(Collectors.toList());
    }

    @Override
    public void add(EtsyProduct product) {
        productDB.add(product);
    }

    @Override
    public int deleteById(String product_id) {
        Optional<EtsyProduct> product_to_delete = productDB.parallelStream()
                .filter(product -> product.getId().equals(product_id))
                .findFirst();

        //Found -> Delete
        if(product_to_delete.isPresent()) {
            productDB.remove(product_to_delete);
            return 1;
        }

        //No object found
        return -1;
    }

    @Override
    public List<EtsyProduct> getAll() {
        return productDB;
    }
}
