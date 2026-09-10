package com.dash.ecommerce.backend.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dash.ecommerce.backend.product.dto.ProductRequest;
import com.dash.ecommerce.backend.product.dto.ProductResponse;
import com.dash.ecommerce.backend.product.entity.Product;
import com.dash.ecommerce.backend.product.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@PostMapping
	public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
		return productService.createProduct(request);
	}
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping
	public List<ProductResponse> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
	 
}
