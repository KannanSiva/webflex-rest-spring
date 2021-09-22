package com.spring.spring5webflexrest.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.spring5webflexrest.domain.Vendor;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String>{

}
