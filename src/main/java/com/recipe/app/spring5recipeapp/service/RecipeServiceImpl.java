package com.recipe.app.spring5recipeapp.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.recipe.app.spring5recipeapp.command.RecipeCommand;
import com.recipe.app.spring5recipeapp.converter.RecipeCommandToRecipe;
import com.recipe.app.spring5recipeapp.converter.RecipeToRecipeCommand;
import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private final RecipeRepository recipeRepository;
	@Autowired
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	@Autowired
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {

		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

		if (!optionalRecipe.isPresent()) {
			throw new RuntimeException("Recipe not found!");
		}

		return optionalRecipe.get();
	}

	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand source) {

		Recipe detachRecipe = recipeCommandToRecipe.convert(source);

		Recipe saveRecipe = recipeRepository.save(detachRecipe);
		log.debug("saved recipe Id "+saveRecipe.getId());
		return recipeToRecipeCommand.convert(saveRecipe);
	}

	@Override
	public RecipeCommand findCommandById(Long id) {
		return recipeToRecipeCommand.convert(findById(id));
	}

	@Override
	public void deleteById(Long id) {
		
		recipeRepository.deleteById(id);
	}

}
