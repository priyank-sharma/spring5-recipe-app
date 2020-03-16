package com.recipe.app.spring5recipeapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.app.spring5recipeapp.domain.Category;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;
import com.recipe.app.spring5recipeapp.repository.CategoryRepository;
import com.recipe.app.spring5recipeapp.repository.UnitOfMeasureRepository;

@Controller
public class IndexController {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	public IndexController(CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		categoryRepository = categoryRepository;
		unitOfMeasureRepository = unitOfMeasureRepository;
	}


	@RequestMapping({"","/","/index"})
	public String getIndex() {
		
		Optional<Category> optionalCategory = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		System.out.println("Cat Id: "+optionalCategory.get().getId());
		System.out.println("UOM Id: "+optionalUnitOfMeasure.get().getId());
		
		return "index";
	}
}
