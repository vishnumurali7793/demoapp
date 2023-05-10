package com.project.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Double quantity;
	private List<SaleDTO> sales;

}
