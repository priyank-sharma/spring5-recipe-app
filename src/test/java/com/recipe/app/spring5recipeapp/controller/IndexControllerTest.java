package com.recipe.app.spring5recipeapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.service.RecipeService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {
	
	@Mock
	RecipeService recipeService;
	@Mock
	Model model;
	
	IndexController indexController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
		
	}

	@Test
	public void testMockMvc() throws Exception {
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	@Test
	public void testGetIndexPage() {
		
		Set<Recipe> recipeData = new HashSet<>();
		recipeData.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipeData.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipeData);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		
		String viewName = indexController.getIndexPage(model);
		
		assertEquals("index", viewName);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}

}
