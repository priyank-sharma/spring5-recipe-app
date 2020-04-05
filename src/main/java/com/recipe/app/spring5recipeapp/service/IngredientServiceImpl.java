package com.recipe.app.spring5recipeapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe.app.spring5recipeapp.command.IngredientsCommand;
import com.recipe.app.spring5recipeapp.converter.IngredientsCommandToIngredients;
import com.recipe.app.spring5recipeapp.converter.IngredientsToIngredientsCommand;
import com.recipe.app.spring5recipeapp.domain.Ingredients;
import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.repository.RecipeRepository;
import com.recipe.app.spring5recipeapp.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
	private IngredientsCommandToIngredients ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientsToIngredientsCommand ingredientsToIngredientsCommand,
			UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientsCommandToIngredients ingredientCommandToIngredient) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientsCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		// TODO Auto-generated method stub
		Optional<Recipe> optionalRecipe = recipeRepository.findById(Long.valueOf(recipeId));

		if (!optionalRecipe.isPresent()) {
			log.error("Recipe is not present with Id " + recipeId);
		}
		Recipe recipe = optionalRecipe.get();

		System.out.println("recipe ingredient " + recipe.getIngredients());

		/*
		 * Optional<IngredientsCommand> optionalIngredientCommand =
		 * recipe.getIngredients().stream(). filter(ingredient ->
		 * ingredient.getId().equals(ingredientId)). map(ingredient ->
		 * ingredientsToIngredientsCommand.convert(ingredient)).findFirst();
		 */

		Optional<IngredientsCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientsToIngredientsCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			// todo impl error handling
			log.error("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	public IngredientsCommand saveIngredientCommand(IngredientsCommand command) {
		// TODO Auto-generated method stub
		
		Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());
		
		if(!optionalRecipe.isPresent()) {
			
			log.error("Recipe is not present for ingredient "+command.getId());
		}
		
		Recipe recipe = optionalRecipe.get();
		
		Optional<Ingredients> optionalIngredients = recipe.getIngredients()
				.stream()
				.filter(ingredients -> ingredients.getId().equals(command.getId()))
				.findFirst();
		
		if(optionalIngredients.isPresent()) {
			
			Ingredients ingredientFound = optionalIngredients.get();
			ingredientFound.setDescription(command.getDescription());
			ingredientFound.setAmount(command.getAmount());
			ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
					.findById(command.getUnitOfMeasure().getId())
					.orElseThrow(() -> new RuntimeException("UOM not found")));
		}else {
			
			recipe.addIngredient(ingredientCommandToIngredient.convert(command));
		}
		
		Recipe saveRecipe = recipeRepository.save(recipe);
		
		return ingredientsToIngredientsCommand.convert(saveRecipe.getIngredients().stream()
				.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
				.findFirst().get());
	}

}
