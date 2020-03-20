package com.recipe.app.spring5recipeapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.app.spring5recipeapp.domain.Category;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;
import com.recipe.app.spring5recipeapp.repository.CategoryRepository;
import com.recipe.app.spring5recipeapp.repository.UnitOfMeasureRepository;
import com.recipe.app.spring5recipeapp.service.RecipeService;

@Controller
public class IndexController {
	
	
	private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
