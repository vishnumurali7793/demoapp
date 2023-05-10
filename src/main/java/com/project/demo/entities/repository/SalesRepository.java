package com.project.demo.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.entities.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {

}
