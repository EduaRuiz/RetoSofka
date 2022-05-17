package com.eduarruiz.retosofka.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.eduarruiz.retosofka.commons.GenericImpl;
import com.eduarruiz.retosofka.model.Category;
import com.eduarruiz.retosofka.repository.CategoryRepository;
import com.eduarruiz.retosofka.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericImpl<Category, Long> implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	protected CrudRepository<Category, Long> getDao() {
		return categoryRepository;
	}

}
