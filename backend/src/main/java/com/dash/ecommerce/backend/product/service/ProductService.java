package com.dash.ecommerce.backend.product.service;


import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.product.entity.Product;
import com.dash.ecommerce.backend.product.repository.ProductRepository;

@Service
public class ProductService {
	
	 private final ProductRepository productRepository;

     public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
     }
	 
     public Product createProduct(Product product) {
    	 return productRepository.save(product);
     }
}
