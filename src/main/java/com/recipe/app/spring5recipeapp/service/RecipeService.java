package com.recipe.app.spring5recipeapp.service;

import java.util.Set;

import com.recipe.app.spring5recipeapp.domain.Recipe;

public interface RecipeService {

	 Set<Recipe> getRecipes();
}
