package com.recipe.app.spring5recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.domain.Category;
import com.recipe.app.spring5recipeapp.domain.Difficulty;
import com.recipe.app.spring5recipeapp.domain.Ingredients;
import com.recipe.app.spring5recipeapp.domain.Notes;
import com.recipe.app.spring5recipeapp.domain.Recipe;
import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;
import com.recipe.app.spring5recipeapp.repository.CategoryRepository;
import com.recipe.app.spring5recipeapp.repository.RecipeRepository;
import com.recipe.app.spring5recipeapp.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipes());
		log.debug("loading bootstrap data");
	}

	public List<Recipe> getRecipes() {
		List<Recipe> recipeList = new ArrayList<Recipe>();

		// Get UOM
		Optional<UnitOfMeasure> optionalTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");

		if (!optionalTeaspoon.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");

		if (!optionalTablespoon.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalCup = unitOfMeasureRepository.findByDescription("Cup");

		if (!optionalCup.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalPinch = unitOfMeasureRepository.findByDescription("Pinch");

		if (!optionalPinch.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalOunce = unitOfMeasureRepository.findByDescription("Ounce");

		if (!optionalOunce.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalEach = unitOfMeasureRepository.findByDescription("Each");

		if (!optionalEach.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}

		Optional<UnitOfMeasure> optionalDash = unitOfMeasureRepository.findByDescription("Dash");

		if (!optionalDash.isPresent()) {
			throw new RuntimeException("Expected UOM not present");
		}
		
		//get optionals
		UnitOfMeasure teaspoonUom = optionalTeaspoon.get();
		UnitOfMeasure tablespoonUom = optionalTablespoon.get();
		UnitOfMeasure cupUom = optionalCup.get();
		UnitOfMeasure pinchUom = optionalPinch.get();
		UnitOfMeasure ounceUom = optionalOunce.get();
		UnitOfMeasure eachUom = optionalEach.get();
		UnitOfMeasure dashUom = optionalDash.get();
		
		//Get catedories
		Optional<Category> optionalAmerican = categoryRepository.findByDescription("American");
		
		if(!optionalAmerican.isPresent()) {
			throw new RuntimeException("Expected category not present");
		}

		Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
		
		if(!optionalMexican.isPresent()) {
			throw new RuntimeException("Expected category not present");
		}
		
		Category americanCategory = optionalAmerican.get();
		Category mexicanCategory = optionalMexican.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        
        Notes guacNotes = new Notes();
        guacNotes.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        
        guacRecipe.setNotes(guacNotes);
		
        guacRecipe.addIngredient(new Ingredients("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredients("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredients("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredients("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredients("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredients("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredients("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredients("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipeList.add(guacRecipe);
        
        
        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe.addIngredient(new Ingredients("Ancho Chili Powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredients("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredients("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredients("Sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredients("Salt", new BigDecimal(".5"), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredients("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredients("finely grated orange zestr", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredients("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredients("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredients("boneless chicken thighs", new BigDecimal(4), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredients("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredients("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredients("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredients("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredients("cherry tomatoes, halved", new BigDecimal(".5"), pinchUom));
        tacosRecipe.addIngredient(new Ingredients("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredients("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredients("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredients("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipeList.add(tacosRecipe);
        
        
		return recipeList;
	}
}
