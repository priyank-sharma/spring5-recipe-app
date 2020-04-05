package com.recipe.app.spring5recipeapp.service;

import com.recipe.app.spring5recipeapp.command.IngredientsCommand;

public interface IngredientService {

	public IngredientsCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	IngredientsCommand saveIngredientCommand(IngredientsCommand command);
}
