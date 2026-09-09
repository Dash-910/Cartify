package com.dash.ecommerce.backend.product.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.product.entity.Product;
import com.dash.ecommerce.backend.product.exception.ProductNotFoundException;
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
     
     public Product getProductById(Long id) {
    	    return productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));
     }
     
     public List<Product> getAllProducts() {
    	    return productRepository.findAll();
     }
     
     public Product updateProduct(Long id, Product product) {

    	    Product existingProduct = productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));

    	    existingProduct.setName(product.getName());
    	    existingProduct.setGroupId(product.getGroupId());
    	    existingProduct.setDescription(product.getDescription());
    	    existingProduct.setPrice(product.getPrice());
    	    existingProduct.setStock(product.getStock());

    	    return productRepository.save(existingProduct);
     }
     
     public void deleteProduct(Long id) {
    	    Product existingProduct = productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));

    	    productRepository.delete(existingProduct);
     }
}
