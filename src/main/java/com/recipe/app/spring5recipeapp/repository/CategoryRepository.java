package com.recipe.app.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipe.app.spring5recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
