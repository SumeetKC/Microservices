package com.microservice.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
