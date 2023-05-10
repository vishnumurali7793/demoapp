package com.project.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.demo.entities.Product;
import com.project.demo.entities.Sale;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);
	}

	@Bean
	List<Product> products() {
		List<Product> products = new ArrayList<>();
		products.add(new Product(1l, "iPhone 9", "An apple mobile which is nothing like apple", 549.0, 94.0, null));
		products.add(new Product(2l, "iPhone X", "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...", 899.0, 34.0, null));
		products.add(new Product(3l, "Samsung Universe 9", "Samsung's new variant which goes beyond Galaxy to the Universe", 1249.0, 36.0, null));
		products.add(new Product(4l, "OPPOF19", "OPPO F19 is officially announced on April 2021.", 280.0, 123.0, null));
		products.add(new Product(5l, "Huawei P30", "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.", 499.0, 32.0, null));
		return products;
	}
	
	@Bean
	List<Sale> sales(){
		List<Sale> sales = new ArrayList<>();
		sales.add(new Sale(1l, new Product(1l), 2.0, new Date()));
		sales.add(new Sale(2l, new Product(2l), 5.0, new Date()));
		sales.add(new Sale(3l, new Product(3l), 1.0, new Date()));
		sales.add(new Sale(4l, new Product(4l), 10.0, new Date()));
		sales.add(new Sale(5l, new Product(5l), 9.0, new Date()));
		return sales;
	}

}
