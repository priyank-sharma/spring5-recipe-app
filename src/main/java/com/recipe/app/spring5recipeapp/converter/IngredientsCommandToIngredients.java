package com.recipe.app.spring5recipeapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.IngredientsCommand;
import com.recipe.app.spring5recipeapp.domain.Ingredients;

import lombok.Synchronized;

@Component
public class IngredientsCommandToIngredients implements Converter<IngredientsCommand, Ingredients> {

	@Autowired
	private UOMCommandToUom uomConverter;
	
	public IngredientsCommandToIngredients(UOMCommandToUom uomConverter) {
        this.uomConverter = uomConverter;
    }
	
	@Synchronized
	@Nullable
	@Override
	public Ingredients convert(IngredientsCommand source) {

		if (source == null) {
            return null;
        }
		
		final Ingredients ingredients = new Ingredients();
		
		ingredients.setId(source.getId());
		ingredients.setAmount(source.getAmount());
		ingredients.setDescription(source.getDescription());
		ingredients.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
		
		return ingredients;
	}

}
