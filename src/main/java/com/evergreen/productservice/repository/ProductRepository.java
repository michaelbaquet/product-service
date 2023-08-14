package com.evergreen.productservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.evergreen.productservice.model.Product;

// public interface ProductRepository extends MongoRepository<Product, String> {
// }

public class ProductRepository {

    private static List<Product> products = new ArrayList<>();

    public void save(Product product) {
        product.setId(String.valueOf(Math.random()));
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

}