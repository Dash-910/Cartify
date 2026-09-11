package com.dash.ecommerce.backend.productgroup.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.productgroup.dto.ProductGroupRequest;
import com.dash.ecommerce.backend.productgroup.dto.ProductGroupResponse;
import com.dash.ecommerce.backend.productgroup.entity.ProductGroup;
import com.dash.ecommerce.backend.productgroup.exception.ProductGroupNotFoundException;
import com.dash.ecommerce.backend.productgroup.repository.ProductGroupRepository;


@Service
public class ProductGroupService {
	
	private final ProductGroupRepository productGroupRepository;
	
	public ProductGroupService(ProductGroupRepository productGroupRepository) {
	        this.productGroupRepository = productGroupRepository;
	}
	
	public ProductGroupResponse createProductGroup(ProductGroupRequest request) {

	        ProductGroup productGroup = new ProductGroup();

	        productGroup.setName(request.getName());
	        productGroup.setDescription(request.getDescription());

	        ProductGroup savedProductGroup =
	                productGroupRepository.save(productGroup);

	        return toResponse(savedProductGroup);
	}
	
	public ProductGroupResponse getProductGroupById(Long id) {

        ProductGroup productGroup = productGroupRepository.findById(id)
                .orElseThrow(() -> new ProductGroupNotFoundException(id));

        return toResponse(productGroup);
    }
	
	public List<ProductGroupResponse> getAllProductGroups() {

	        return productGroupRepository.findAll()
	                .stream()
	                .map(this::toResponse)
	                .toList();
	}
	
	public ProductGroupResponse updateProductGroup(
	            Long id,
	            ProductGroupRequest request) {

	        ProductGroup productGroup = productGroupRepository.findById(id)
	                .orElseThrow(() -> new ProductGroupNotFoundException(id));

	        productGroup.setName(request.getName());
	        productGroup.setDescription(request.getDescription());

	        ProductGroup updatedProductGroup =
	                productGroupRepository.save(productGroup);

	        return toResponse(updatedProductGroup);
	 }
	
	 public void deleteProductGroup(Long id) {

	        ProductGroup productGroup = productGroupRepository.findById(id)
	                .orElseThrow(() -> new ProductGroupNotFoundException(id));

	        productGroupRepository.delete(productGroup);
	 }
	 
	 private ProductGroupResponse toResponse(ProductGroup productGroup) {

	        return new ProductGroupResponse(
	                productGroup.getId(),
	                productGroup.getName(),
	                productGroup.getDescription()
	        );
	 }
}
