package com.spring.spring5webflexrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.spring5webflexrest.domain.Category;
import com.spring.spring5webflexrest.domain.Vendor;
import com.spring.spring5webflexrest.repository.CategoryRepository;
import com.spring.spring5webflexrest.repository.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final VendorRepository vendorRepository;

	public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
		this.categoryRepository = categoryRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		if(categoryRepository.count().block() == 0){
		    
			System.out.println("#### LOADING DATA ON BOOTSTRAP #####");

			categoryRepository.save(Category.builder()
					.description("Fruits").build()).block();

			categoryRepository.save(Category.builder()
					.description("Nuts").build()).block();

			categoryRepository.save(Category.builder()
					.description("Breads").build()).block();

			categoryRepository.save(Category.builder()
					.description("Meats").build()).block();

			categoryRepository.save(Category.builder()
					.description("Eggs").build()).block();

	System.out.println("Loaded Categories: " + categoryRepository.count().block());

			vendorRepository.save(Vendor.builder()
					.firstName("Siva")
					.lastName("Mani").build()).block();

			vendorRepository.save(Vendor.builder()
					.firstName("Kannan")
					.lastName("Mani").build()).block();

			vendorRepository.save(Vendor.builder()
					.firstName("Priya")
					.lastName("Muthu").build()).block();

			vendorRepository.save(Vendor.builder()
					.firstName("Karthik")
					.lastName("Muthu").build()).block();

			vendorRepository.save(Vendor.builder()
					.firstName("Dharshini")
					.lastName("Saravanan").build()).block();

	System.out.println("Loaded Vendors: " + vendorRepository.count().block());

		}



	}
}