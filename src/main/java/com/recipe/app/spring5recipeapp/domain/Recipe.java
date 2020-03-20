package com.recipe.app.spring5recipeapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Recipe {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;
	@Lob
	private String directions;
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	@Lob
	private byte image[];
	
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	Set<Ingredients> ingredients = new HashSet<Ingredients>();
	
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "recipe_category", 
	joinColumns = @JoinColumn(name = "recipe_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categories = new HashSet<Category>();
	
	public Recipe addIngredient(Ingredients ingredients) {
		ingredients.setRecipe(this);
		this.ingredients.add(ingredients);
		return this;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
		notes.setRecipe(this);
	}
}
