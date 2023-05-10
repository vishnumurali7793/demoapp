package com.project.demo.controller;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.ResponseDTO;
import com.project.demo.entities.Product;
import com.project.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1.0")
@Slf4j
public class ProductController {
	@Autowired
	ProductService productService;

	ResponseDTO responseDTO;

	@GetMapping("/hello")
	public String helloworld() {
		return "hello world";
	}

	@GetMapping("/product")
	public ResponseEntity<ResponseDTO> getAllProduct(@RequestHeader HttpHeaders httpHeaders, Pageable page) {
		responseDTO = new ResponseDTO();
		try {
			responseDTO.setErrorCode(null);
			responseDTO.setErrorMessage(null);
			responseDTO.setMessage("success");
			responseDTO.setStatusCode(1l);
			if (httpHeaders.containsKey("data-source") && httpHeaders.get("data-source").contains("db")) {
				Page<Product> pageProduct = productService.findAllProducts(page);
				if (pageProduct != null && !pageProduct.getContent().isEmpty()) {
					responseDTO.setData(pageProduct.getContent());
				} else {
					responseDTO.setData(Collections.emptyList());
				}
			} else {
				responseDTO.setData(productService.getAllProducts());
			}
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (Exception e) {
			log.error("Exception occured while sending products response, {}", e.getCause());
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error");
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

	@PostMapping("/product")
	public ResponseEntity<ResponseDTO> addProduct(@RequestBody(required = true) Product product) {
		responseDTO = new ResponseDTO();
		if (product != null && !product.getName().isEmpty() && !product.getPrice().isNaN()) {
			boolean isSuccessful = productService.addProduct(product);
			if (isSuccessful) {
				responseDTO.setErrorCode(null);
				responseDTO.setErrorMessage(null);
				responseDTO.setMessage("Data Successfully Inserted");
				responseDTO.setStatusCode(1l);
				responseDTO.setData(product);
			} else {
				responseDTO.setErrorCode(500l);
				responseDTO.setErrorMessage("Error Occured While Insertion");
				responseDTO.setMessage("Failure");
				responseDTO.setStatusCode(0l);
				responseDTO.setData(product);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable(required = true) Long id) {
		responseDTO = new ResponseDTO();
		if (id != null && id > 0l) {
			try {
				boolean isSuccessful = productService.deleteProduct(id);
				if (isSuccessful) {
					responseDTO.setErrorCode(null);
					responseDTO.setErrorMessage(null);
					responseDTO.setMessage("Data Successfully Deleted");
					responseDTO.setStatusCode(1l);
					responseDTO.setData(new HashMap<String, Long>().put("id", id));
				}
			} catch (Exception e) {
				e.printStackTrace();
				responseDTO.setErrorCode(500l);
				responseDTO.setErrorMessage("Error Occured While Insertion. " + e.getCause());
				responseDTO.setMessage("Failure");
				responseDTO.setStatusCode(0l);
				responseDTO.setData(null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ResponseDTO> updateProduct(@PathVariable(required = true) Long id,
			@RequestBody(required = true) Product product) {
		responseDTO = new ResponseDTO();
		if (id != null && id > 0) {
			try {
				boolean ifExists = productService.checkProductExistsOrNot(id);
				if (ifExists) {
					product.setId(id);
					productService.updateProduct(product);
					responseDTO.setErrorCode(null);
					responseDTO.setErrorMessage(null);
					responseDTO.setMessage("Data Successfully Updated");
					responseDTO.setStatusCode(1l);
					responseDTO.setData(product);
				}
			} catch (BeansException e) {
				responseDTO.setErrorCode(500l);
				responseDTO.setErrorMessage("Error Occured While Update");
				responseDTO.setMessage("Failure");
				responseDTO.setStatusCode(0l);
				responseDTO.setData(product);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseDTO> getProductById(@PathVariable(required = true) Long id) {
		responseDTO = new ResponseDTO();
		try {
			if (id != null && id > 0) {
				Product product = productService.getProductById(id);
				if (product != null) {
					responseDTO.setErrorCode(null);
					responseDTO.setErrorMessage(null);
					responseDTO.setMessage("Success");
					responseDTO.setStatusCode(1l);
					responseDTO.setData(product);
				} else {
					responseDTO.setErrorCode(404l);
					responseDTO.setErrorMessage("Product Not Found");
					responseDTO.setMessage("Failure");
					responseDTO.setStatusCode(0l);
					responseDTO.setData(product);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
				}
			}
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Error Occured While Fetching Product");
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@GetMapping("/revenue")
	public ResponseEntity<ResponseDTO> getTotalRevenue() {
		responseDTO = new ResponseDTO();
		try {
			responseDTO.setData(productService.getTotalRevenue());
			responseDTO.setErrorCode(null);
			responseDTO.setErrorMessage(null);
			responseDTO.setMessage("Success");
			responseDTO.setStatusCode(1l);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Error Occured While Fetching Product");
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

	@GetMapping("/revenue/product/{id}")
	public ResponseEntity<ResponseDTO> getRevenueByProduct(@PathVariable Long id) {
		responseDTO = new ResponseDTO();
		try {
			if (id != null && id > 0) {
				responseDTO.setData(productService.getRevenueByProduct(id));
				responseDTO.setErrorCode(null);
				responseDTO.setErrorMessage(null);
				responseDTO.setMessage("Success");
				responseDTO.setStatusCode(1l);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			} else {
				responseDTO.setErrorCode(404l);
				responseDTO.setErrorMessage("Product Not Found");
				responseDTO.setMessage("Failure");
				responseDTO.setStatusCode(0l);
				responseDTO.setData(null);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
			}
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Error Occured While Fetching Product");
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

}
