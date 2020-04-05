package com.recipe.app.spring5recipeapp.command;

import java.math.BigDecimal;

import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientsCommand {
	private Long recipeId;
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeasure;
}
