package com.evergreen.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evergreen.productservice.dto.ProductRequest;
import com.evergreen.productservice.dto.ProductResponse;
import com.evergreen.productservice.model.Product;
import com.evergreen.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
// @RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        
        log.info("Product {} is saved!!!!!!!!!!!!!!", product.getId() );
    }

    public List<ProductResponse> getAllProducts() {
        log.info("FETCHING ALL ITEMS", "");
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }


}
