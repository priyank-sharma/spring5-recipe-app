package com.recipe.app.spring5recipeapp.service;

import java.util.Set;

import com.recipe.app.spring5recipeapp.command.RecipeCommand;
import com.recipe.app.spring5recipeapp.domain.Recipe;

public interface RecipeService {

	 Set<Recipe> getRecipes();
	 
	 Recipe findById(Long id);
	 
	 RecipeCommand saveRecipeCommand(RecipeCommand source);
	 
	 RecipeCommand findCommandById(Long id);
	 
	 void deleteById(Long id);
}
