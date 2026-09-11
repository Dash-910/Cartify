package com.dash.ecommerce.backend.product.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.product.dto.ProductRequest;
import com.dash.ecommerce.backend.product.dto.ProductResponse;
import com.dash.ecommerce.backend.product.entity.Product;
import com.dash.ecommerce.backend.product.exception.ProductNotFoundException;
import com.dash.ecommerce.backend.product.repository.ProductRepository;
import com.dash.ecommerce.backend.productgroup.entity.ProductGroup;
import com.dash.ecommerce.backend.productgroup.exception.ProductGroupNotFoundException;
import com.dash.ecommerce.backend.productgroup.repository.ProductGroupRepository;

@Service
public class ProductService {
	
	 private final ProductRepository productRepository;
	 private final ProductGroupRepository productGroupRepository;

     public ProductService(ProductRepository productRepository, ProductGroupRepository productGroupRespository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRespository;
     }
	 
     public ProductResponse createProduct(ProductRequest request) {
    	 
    	 //fetch the product group 
    	 ProductGroup productGroup = productGroupRepository.findById(request.getGroupId()).orElseThrow(()-> new ProductGroupNotFoundException(request.getGroupId()));
    	 
    	 Product product = new Product();
    	 
    	 product.setName(request.getName());
    	 product.setProductGroup(productGroup);
    	 product.setDescription(request.getDescription());
         product.setPrice(request.getPrice());
         product.setStock(request.getStock());
         
         Product savedProduct = productRepository.save(product);
         
         return toResponse(savedProduct);
     }
     
     public ProductResponse getProductById(Long id) {
    	   Product product = productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));
    	   
    	   return toResponse(product);
     }
     
     public List<ProductResponse> getAllProducts() {
    	    return productRepository.findAll().stream().map(this::toResponse).toList();
     }
     
     public ProductResponse updateProduct(Long id, ProductRequest request) {

    	    Product existingProduct = productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));
    	    
    	    //fetch the product group 
    	    ProductGroup productGroup = productGroupRepository.findById(request.getGroupId()).orElseThrow(()-> new ProductGroupNotFoundException(request.getGroupId()));
       	 
    	    existingProduct.setName(request.getName());
    	    existingProduct.setProductGroup(productGroup);
    	    existingProduct.setDescription(request.getDescription());
    	    existingProduct.setPrice(request.getPrice());
    	    existingProduct.setStock(request.getStock());
    	    
    	    Product updatedProduct = productRepository.save(existingProduct);
    	    return toResponse(updatedProduct);
     }
     
     public void deleteProduct(Long id) {
    	    Product existingProduct = productRepository.findById(id)
    	            .orElseThrow(() -> new ProductNotFoundException(id));

    	    productRepository.delete(existingProduct);
     }
     
     private ProductResponse toResponse(Product product) {

         return new ProductResponse(
                 product.getId(),
                 product.getName(),
                 product.getProductGroup().getId(),
                 product.getDescription(),
                 product.getPrice(),
                 product.getStock(),
                 product.getCreatedAt(),
                 product.getUpdatedAt()
         );
     }
}
