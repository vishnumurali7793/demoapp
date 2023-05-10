package com.project.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SaleDTO {
	private Long id;
	private ProductDTO productDTO;
	private Double quantity;
	private Date saleDate;

}
