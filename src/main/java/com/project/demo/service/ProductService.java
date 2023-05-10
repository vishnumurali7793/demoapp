package com.project.demo.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.project.demo.dto.ProductDTO;
import com.project.demo.entities.Product;

public interface ProductService {

	Object getAllProducts();

	boolean addProduct(Product product);

	boolean deleteProduct(Long id);

	void updateProduct(Product productToBeUpdated);

	boolean checkProductExistsOrNot(Long id);

	Product getProductById(Long id);

	List<ProductDTO> findAllProducts(Pageable page);

	Object getTotalRevenue();

	Object getRevenueByProduct(Long id);

}
