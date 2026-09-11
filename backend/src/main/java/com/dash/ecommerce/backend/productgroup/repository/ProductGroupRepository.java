package com.dash.ecommerce.backend.productgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dash.ecommerce.backend.productgroup.entity.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

}
