package com.project.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.demo.dto.ProductDTO;
import com.project.demo.entities.Product;
import com.project.demo.entities.Sale;
import com.project.demo.entities.repository.ProductRepository;
import com.project.demo.entities.repository.SalesRepository;
import com.project.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	List<Product> products;

	@Autowired
	ProductRepository productRepository;
	@Autowired
	SalesRepository salesRepository;

	@Override
	public Object getAllProducts() {
		return products;
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			if (product.getId() != null && (product.getId() == products.size() + 1)) {
				products.add(product);
			} else {
				product.setId(products.size() + 1l);
				products.add(product);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Long id) {
		for (Product product : products) {
			if (product != null && product.getId().equals(id)) {
				products.remove(product);
			}
		}
		return true;
	}

	@Override
	public boolean checkProductExistsOrNot(Long id) {
		for (Product product : products) {
			if (product != null && product.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateProduct(Product productToBeUpdated) {
		for (Product product : products) {
			if (product != null && product.getId().equals(productToBeUpdated.getId())) {
				BeanUtils.copyProperties(productToBeUpdated, product);
			}
		}
	}

	@Override
	public Product getProductById(Long id) {
		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<ProductDTO> findAllProducts(Pageable page) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		Page<Product> pageProduct = productRepository.findAll(page);
		if (pageProduct != null && !pageProduct.getContent().isEmpty()) {
			for (Product product : pageProduct.getContent()) {
				productDTOs.add(new ProductDTO(product.getId(), product.getName(), product.getDescription(),
						product.getPrice(), product.getQuantity(), null));
			}
			return productDTOs;
		}
		return Collections.emptyList();
	}

	@Override
	public Object getTotalRevenue() {
		Double grossAmount = 0D;
		try {
			List<Product> productsFromDB = productRepository.findAll();
			if (productsFromDB != null && !productsFromDB.isEmpty()) {
				for (Product product : productsFromDB) {
					Double totalQuantitySold = 0D;
					for (Sale sale : product.getSales()) {
						totalQuantitySold += sale.getQuantity();
					}
					if (totalQuantitySold > 0) {
						grossAmount += totalQuantitySold * product.getPrice();
					}
				}
			}
			return grossAmount;
		} catch (Exception e) {
			return 0D;
		}
	}

	@Override
	public Object getRevenueByProduct(Long id) {
		Double totalQuantitySold = 0D;
		try {
			Product product = productRepository.findById(id).get();
			if (product != null && product.getSales() != null) {
				for (Sale sale : product.getSales()) {
					totalQuantitySold += sale.getQuantity();
				}
				return totalQuantitySold * product.getPrice();
			}
			return 0d;
		} catch (Exception e) {
			return 0d;
		}
	}

}
