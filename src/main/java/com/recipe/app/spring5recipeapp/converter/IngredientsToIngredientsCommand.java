package com.recipe.app.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.IngredientsCommand;
import com.recipe.app.spring5recipeapp.domain.Ingredients;

import lombok.Synchronized;

@Component
public class IngredientsToIngredientsCommand implements Converter<Ingredients, IngredientsCommand> {

	private UOMToUomCommand uomConverter;
	
	public IngredientsToIngredientsCommand(UOMToUomCommand uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}



	@Synchronized
	@Nullable
	@Override
	public IngredientsCommand convert(Ingredients source) {
		
		if (source == null) {
			return null;
		}
		
		final IngredientsCommand ingredientsCommand = new IngredientsCommand();
		
		ingredientsCommand.setId(source.getId());
		ingredientsCommand.setAmount(source.getAmount());
		ingredientsCommand.setDescription(source.getDescription());
		ingredientsCommand.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
		
		return ingredientsCommand;
	}

}
