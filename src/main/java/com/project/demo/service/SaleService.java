package com.project.demo.service;

import com.project.demo.entities.Sale;

public interface SaleService {

	Object getAllSales();

	Sale addSales(Sale sale);

	boolean checkSalesExistOrNot(Long id);

	Sale updateSale(Sale sale);
	
	Sale getSaleById(Long id);

	boolean deleteSale(Long id);

}
