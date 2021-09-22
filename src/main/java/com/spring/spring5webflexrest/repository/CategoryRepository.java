package com.spring.spring5webflexrest.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.spring5webflexrest.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category,String>{
    

}
