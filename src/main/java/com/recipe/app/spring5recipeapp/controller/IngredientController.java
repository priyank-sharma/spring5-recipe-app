package com.recipe.app.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.app.spring5recipeapp.service.IngredientService;
import com.recipe.app.spring5recipeapp.service.RecipeService;
import com.recipe.app.spring5recipeapp.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private RecipeService recipeService;
	private IngredientService ingredientService;
	private UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredients")
	public String getIngredientList(@PathVariable String recipeId, Model model) {
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return "recipe/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
	public String getIngredientDetails(@PathVariable String recipeId,@PathVariable String id, Model model) {
		
		model.addAttribute("ingredient", ingredientService.findRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		return "recipe/ingredient/show";
	}
	
	@GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUOMs());
        return "recipe/ingredient/ingredientform";
    }
}
