package com.recipe.app.spring5recipeapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.RecipeCommand;
import com.recipe.app.spring5recipeapp.domain.Category;
import com.recipe.app.spring5recipeapp.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand  implements Converter<Recipe, RecipeCommand>{
	
	@Autowired
	private CategoryToCategoryCommand categoryConverter;
	@Autowired
	private NotesToNotesCommand notesConverter;
	@Autowired
	private IngredientsToIngredientsCommand ingredientsConverter;
	
	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter,
			IngredientsToIngredientsCommand ingredientsConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.notesConverter = notesConverter;
		this.ingredientsConverter = ingredientsConverter;
	}
	
	
	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientsConverter.convert(ingredient)));
        }

        return command;
	}

}
