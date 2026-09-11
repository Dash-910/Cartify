package com.dash.ecommerce.backend.productgroup.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dash.ecommerce.backend.productgroup.dto.ProductGroupRequest;
import com.dash.ecommerce.backend.productgroup.dto.ProductGroupResponse;
import com.dash.ecommerce.backend.productgroup.service.ProductGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-groups")
public class ProductGroupController {
	
	private final ProductGroupService productGroupService;
	
	public ProductGroupController(ProductGroupService productGroupService) {
	        this.productGroupService = productGroupService;
	}
	
	@PostMapping
	public ProductGroupResponse createProductGroup(
		@Valid @RequestBody ProductGroupRequest request) {
		
		return productGroupService.createProductGroup(request);
	}
	
	@GetMapping("/{id}")
    public ProductGroupResponse getProductGroupById(
            @PathVariable Long id) {

        return productGroupService.getProductGroupById(id);
    }
	
	@GetMapping
    public List<ProductGroupResponse> getAllProductGroups() {

        return productGroupService.getAllProductGroups();
    }
	
	@PutMapping("/{id}")
    public ProductGroupResponse updateProductGroup(
            @PathVariable Long id,
            @Valid @RequestBody ProductGroupRequest request) {

        return productGroupService.updateProductGroup(id, request);
    }
	
	@DeleteMapping("/{id}")
    public void deleteProductGroup(@PathVariable Long id) {

        productGroupService.deleteProductGroup(id);
    }
}
