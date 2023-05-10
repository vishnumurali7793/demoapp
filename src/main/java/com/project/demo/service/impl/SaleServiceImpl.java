package com.project.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.entities.Product;
import com.project.demo.entities.Sale;
import com.project.demo.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	List<Sale> sales;
	@Autowired
	List<Product> products;

	@Override
	public Object getAllSales() {
		for (Sale sale : sales) {
			sale.setProduct(this.findProductFromSale(sale));
		}
		return sales;
	}

	@Override
	public Sale addSales(Sale sale) {
		if (sale.getId() != null && sale.getId() == sales.size() + 1) {
			sale.setSaleDate(new Date());
		} else {
			sale.setId(sales.size() + 1l);
			sale.setSaleDate(new Date());
		}
		sale.setProduct(this.findProductFromSale(sale));
		sales.add(sale);
		return sale;
	}

	private Product findProductFromSale(Sale sale) {
		return products.stream().filter(product -> product.getId().equals(sale.getProduct().getId())).findFirst().get();
	}

	@Override
	public boolean checkSalesExistOrNot(Long id) {
		return sales.stream().filter(sale -> sale.getId().equals(id)).findAny().isPresent();
	}

	@Override
	public Sale updateSale(Sale sale) {
		for (Sale s : sales) {
			if (sale.getId().equals(s.getId())) {
				BeanUtils.copyProperties(sale, s);
				s.setProduct(this.findProductFromSale(s));
			}
			return s;
		}
		return null;
	}

	@Override
	public Sale getSaleById(Long id) {
		Sale sale = sales.stream().filter(s -> s.getId().equals(id)).findFirst().get();
		sale.setProduct(this.findProductFromSale(sale));
		return sale;
	}

	@Override
	public boolean deleteSale(Long id) {
		for (Sale sale : sales) {
			if (sale.getId().equals(id)) {
				sales.remove(sale);
			}
			return true;
		}
		return false;
	}

}
