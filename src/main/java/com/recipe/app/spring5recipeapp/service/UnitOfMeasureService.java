package com.recipe.app.spring5recipeapp.service;

import java.util.Set;

import com.recipe.app.spring5recipeapp.command.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	
	 Set<UnitOfMeasureCommand> listAllUOMs();

}
