package com.eduarruiz.retosofka.service;

import java.util.List;

import com.eduarruiz.retosofka.commons.GenericService;
import com.eduarruiz.retosofka.model.Question;

public interface QuestionService extends GenericService<Question, String> {

  public List<Question> findByIdCategory(Long idCategory);

}