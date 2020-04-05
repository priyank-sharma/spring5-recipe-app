package com.recipe.app.spring5recipeapp.command;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.recipe.app.spring5recipeapp.domain.Category;
import com.recipe.app.spring5recipeapp.domain.Difficulty;
import com.recipe.app.spring5recipeapp.domain.Ingredients;
import com.recipe.app.spring5recipeapp.domain.Notes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private byte image[];
	private NotesCommand notes;
	private Set<IngredientsCommand> ingredients = new HashSet<>();
	private Set<CategoryCommand> categories = new HashSet<>();
}
