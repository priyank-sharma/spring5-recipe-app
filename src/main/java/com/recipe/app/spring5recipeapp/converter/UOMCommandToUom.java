package com.recipe.app.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.UnitOfMeasureCommand;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UOMCommandToUom implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		
		if (source == null) {
			return null;
		}
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		
		return uom;
	}

}
