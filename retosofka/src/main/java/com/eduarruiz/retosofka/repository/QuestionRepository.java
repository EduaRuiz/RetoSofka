package com.eduarruiz.retosofka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import com.eduarruiz.retosofka.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

  @Query
	List<Question> findByIdCategory(Long idCategory);

}