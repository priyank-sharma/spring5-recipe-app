package com.recipe.app.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.app.spring5recipeapp.command.RecipeCommand;
import com.recipe.app.spring5recipeapp.service.RecipeService;

@Controller
public class RecipeController {

	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		
		return "recipe/show";
	}
	
	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeForm";
	}
	
	@RequestMapping("recipe/{id}/update")
	public String update(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/recipeForm";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/" +savedCommand.getId() +"/show" ;
	}
	
	@RequestMapping("recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
}
