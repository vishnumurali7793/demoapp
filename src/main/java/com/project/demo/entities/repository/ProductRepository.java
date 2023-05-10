package com.project.demo.entities.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable page);


}
