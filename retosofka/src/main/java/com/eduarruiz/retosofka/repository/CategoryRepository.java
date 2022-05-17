package com.eduarruiz.retosofka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eduarruiz.retosofka.model.Category;

public interface CategoryRepository extends MongoRepository<Category, Long> {

}
