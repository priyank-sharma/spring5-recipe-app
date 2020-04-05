package com.recipe.app.spring5recipeapp.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	
	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	@Test
	public void testGetId() throws Exception{
		Long idValue = 4L;
		
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
	}

	@Test
	public void testGetRecipes() {
	}

}
