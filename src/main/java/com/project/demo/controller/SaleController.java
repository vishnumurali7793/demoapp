package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.ResponseDTO;
import com.project.demo.entities.Sale;
import com.project.demo.service.ProductService;
import com.project.demo.service.SaleService;

@RestController
@RequestMapping("/api/v1.0")
public class SaleController {
	@Autowired
	SaleService saleService;
	@Autowired
	ProductService productService;

	ResponseDTO responseDTO;

	@GetMapping("/sale")
	public ResponseEntity<ResponseDTO> getAllSales() {
		responseDTO = new ResponseDTO();
		try {
			responseDTO.setErrorCode(null);
			responseDTO.setErrorMessage(null);
			responseDTO.setMessage("Success");
			responseDTO.setStatusCode(1l);
			responseDTO.setData(saleService.getAllSales());
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error. " + e.getCause());
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@PostMapping("/sale")
	public ResponseEntity<ResponseDTO> addSales(@RequestBody(required = true) Sale sale) {
		responseDTO = new ResponseDTO();
		try {
			if (sale != null && sale.getProduct() != null && sale.getQuantity() != null) {
				if (productService.checkProductExistsOrNot(sale.getProduct().getId())) {
					sale = saleService.addSales(sale);
					responseDTO.setData(sale);
					responseDTO.setMessage("Success");
					responseDTO.setStatusCode(1l);
					responseDTO.setErrorCode(null);
					responseDTO.setErrorMessage(null);
				} else {
					responseDTO.setData(null);
					responseDTO.setMessage("Product Not Existing In Database");
					responseDTO.setStatusCode(0l);
					responseDTO.setErrorCode(404l);
					responseDTO.setErrorMessage("Can't Create Sale, Invalid Product Selected.");
				}
			}
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error. " + e.getCause());
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@PutMapping("/sale/{id}")
	public ResponseEntity<ResponseDTO> editSales(@PathVariable(required = true) Long id,
			@RequestBody(required = true) Sale sale) {
		responseDTO = new ResponseDTO();
		try {
			if (id != null && sale != null) {
				boolean ifExist = saleService.checkSalesExistOrNot(id);
				if (ifExist) {
					sale.setId(id);
					sale = saleService.updateSale(sale);
					if (sale != null) {
						responseDTO.setData(sale);
						responseDTO.setMessage("Success");
						responseDTO.setStatusCode(1l);
						responseDTO.setErrorCode(null);
						responseDTO.setErrorMessage(null);
					}
					return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
				}
				responseDTO.setData(null);
				responseDTO.setMessage("Sale Not Existing In Database");
				responseDTO.setStatusCode(0l);
				responseDTO.setErrorCode(404l);
				responseDTO.setErrorMessage("Sale Not Found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
			}
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error. " + e.getCause());
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@GetMapping("/sale/{id}")
	public ResponseEntity<ResponseDTO> getSalesById(@PathVariable(required = true) Long id) {
		responseDTO = new ResponseDTO();
		try {
			if (id != null && id > 0) {
				responseDTO.setData(saleService.getSaleById(id));
				responseDTO.setMessage("Success");
				responseDTO.setStatusCode(1l);
				responseDTO.setErrorCode(null);
				responseDTO.setErrorMessage(null);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			} else {
				responseDTO.setErrorCode(400l);
				responseDTO.setErrorMessage("Invalid ID");
				responseDTO.setMessage("Failure");
				responseDTO.setStatusCode(0l);
				responseDTO.setData(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
			}
		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error. " + e.getCause());
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

	@DeleteMapping("/sale/{id}")
	public ResponseEntity<ResponseDTO> deleteSale(@PathVariable(required = true) Long id) {
		responseDTO = new ResponseDTO();
		try {
			if (id != null && id > 0) {
				boolean isSuccess = saleService.deleteSale(id);
				if (isSuccess) {
					responseDTO.setData(null);
					responseDTO.setMessage("Success");
					responseDTO.setStatusCode(1l);
					responseDTO.setErrorCode(null);
					responseDTO.setErrorMessage(null);
					return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
				}
			}
			responseDTO.setErrorCode(400l);
			responseDTO.setErrorMessage("Invalid ID");
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

		} catch (Exception e) {
			responseDTO.setErrorCode(500l);
			responseDTO.setErrorMessage("Internal Server Error. " + e.getCause());
			responseDTO.setMessage("Failure");
			responseDTO.setStatusCode(0l);
			responseDTO.setData(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
	}

}
