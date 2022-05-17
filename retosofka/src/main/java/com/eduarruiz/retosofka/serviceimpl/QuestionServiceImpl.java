package com.eduarruiz.retosofka.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eduarruiz.retosofka.commons.GenericImpl;
import com.eduarruiz.retosofka.model.Question;
import com.eduarruiz.retosofka.repository.QuestionRepository;
import com.eduarruiz.retosofka.service.QuestionService;

@Service
public class QuestionServiceImpl extends GenericImpl<Question, String> implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	protected CrudRepository<Question, String> getDao() {
		return questionRepository;
	}

	//este metodo filtra las preguntas por categoría, ordena las preguntas aleatoriamente y asigna la respuesta correcta 
	@Override
	public List<Question> findByIdCategory(Long idCategory) {
		List<Question> questions = new ArrayList<Question>();
		for (Question i:questionRepository.findByIdCategory(idCategory)){
			List<String> options = new ArrayList<String>();
				for(String j:i.getOptions()){
					options.add(j);
				}
				Collections.shuffle(options);
				String[] newOptions = options.toArray(new String[0]);
				i.setOptions(newOptions);
				questions.add(i);
		}
		return questions;
	}
}