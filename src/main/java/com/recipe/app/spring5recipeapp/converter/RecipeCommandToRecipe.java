package com.recipe.app.spring5recipeapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.RecipeCommand;
import com.recipe.app.spring5recipeapp.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{
	
	@Autowired
	private CategoryCommandToCategory categoryConverter;
	@Autowired
	private NotesCommandToNotes notesConverter;
	@Autowired
	private IngredientsCommandToIngredients ingredientsConverter;
	
	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter,
			IngredientsCommandToIngredients ingredientsConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.notesConverter = notesConverter;
		this.ingredientsConverter = ingredientsConverter;
	}
	
	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		
		if(source == null) {
			return null;
		}
		
		final Recipe recipe = new Recipe();
		
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setNotes(notesConverter.convert(source.getNotes()));

		if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientsConverter.convert(ingredient)));
        }
        
		return recipe;
	}
}
