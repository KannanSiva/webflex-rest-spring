package com.spring.spring5webflexrest.controllers;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.spring5webflexrest.domain.Category;
import com.spring.spring5webflexrest.repository.CategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CategoryControllerTest {
    
    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;
    
    @BeforeEach
    public void setUp() throws Exception{
	
	categoryRepository=Mockito.mock(CategoryRepository.class);
	categoryController= new CategoryController(categoryRepository);
	webTestClient=WebTestClient.bindToController(categoryController).build();
	
    }
    
    @Test
    void testList() {
	BDDMockito.given(categoryRepository.findAll()).willReturn(Flux.just(Category.builder().description("Cat1").build(),
		Category.builder().description("cat2").build()));
	
	webTestClient.get()
			.uri("/api/v1/categories/")
			.exchange()
			.expectBodyList(Category.class)
			.hasSize(2);
	
    }

    @Test
    void testGetById() {
	BDDMockito.given(categoryRepository.findById("someId")).willReturn(Mono.just(Category.builder().description("Cat").build()));
	
	webTestClient.get()
	.uri("/api/v1/categories/someId")
	.exchange()
	.expectBody(Category.class);
    }
    
    @Test
    void testCreateCategory() {
	BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
        .willReturn(Flux.just(Category.builder().description("descrp").build()));

Mono<Category> catToSaveMono = Mono.just(Category.builder().description("Some Cat").build());

webTestClient.post()
        .uri("/api/v1/categories")
        .body(catToSaveMono, Category.class)
        .exchange()
        .expectStatus()
        .isCreated();
    }
    
    
    @Test
    void testUpdate() {
	BDDMockito.given(categoryRepository.save(any(Category.class)))
	.willReturn(Mono.just(Category.builder().build()));
	
	Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("Some Cat").build());
	
	webTestClient.put()
			.uri("/api/v1/categories/asdf")
			.body(catToUpdateMono,Category.class)
			.exchange()
			.expectStatus()
			.isOk();
    }
    
}

