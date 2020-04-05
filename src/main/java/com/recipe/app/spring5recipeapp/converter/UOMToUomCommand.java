package com.recipe.app.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.UnitOfMeasureCommand;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UOMToUomCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {

		if (source == null) {
			return null;
		}
		
		final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		
		uomCommand.setId(source.getId());
		uomCommand.setDescription(source.getDescription());
		
		return uomCommand;
	}

}
