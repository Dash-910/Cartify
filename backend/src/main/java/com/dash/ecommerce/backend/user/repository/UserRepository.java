package com.dash.ecommerce.backend.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dash.ecommerce.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
