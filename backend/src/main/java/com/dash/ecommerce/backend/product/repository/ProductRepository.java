package com.dash.ecommerce.backend.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dash.ecommerce.backend.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}



