package com.recipe.app.spring5recipeapp.service;


import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.repository.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeService recipeService;
	
	@Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        //recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() throws Exception {
    	
    	System.out.println("tada");
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        System.out.println("cha cha");
        
        
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

}
