package com.spring.spring5webflexrest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.spring5webflexrest.domain.Category;
import com.spring.spring5webflexrest.domain.Vendor;
import com.spring.spring5webflexrest.repository.VendorRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class VendorControllerTest {
    
    VendorController vendorController;
    VendorRepository vendorRepository;    
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() throws Exception {
	
	vendorRepository=Mockito.mock(VendorRepository.class);
	vendorController= new VendorController(vendorRepository);
	webTestClient=WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void testList() {
	BDDMockito.given(vendorRepository.findAll()).willReturn(Flux.just(Vendor.builder().firstName("Berlin").lastName("AlansoPedro").build(),
		Vendor.builder().firstName("Siva").lastName("Berlin").build()));
	
	webTestClient.get()
			.uri("/api/v1/vendors")
			.exchange()
			.expectBodyList(Vendor.class)
			.hasSize(2);
	
    }

    @Test
    void testGetById() {
	BDDMockito.given(vendorRepository.findById("someId")).willReturn(Mono.just(Vendor.builder().firstName("Berlin").lastName("AlansoPedro").build()));
	
	webTestClient.get()
	.uri("/api/v1/vendors/someId")
	.exchange()
	.expectBody(Vendor.class);
    }

}
