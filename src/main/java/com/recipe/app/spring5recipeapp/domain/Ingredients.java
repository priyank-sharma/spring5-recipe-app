package com.recipe.app.spring5recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;
	
	@ToString.Exclude
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
	
	public Ingredients() {}

	public Ingredients(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
		// TODO Auto-generated constructor stub
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public Ingredients(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
		// TODO Auto-generated constructor stub
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
		this.recipe = recipe;
	}
}
