package com.recipe.app.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipe.app.spring5recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
