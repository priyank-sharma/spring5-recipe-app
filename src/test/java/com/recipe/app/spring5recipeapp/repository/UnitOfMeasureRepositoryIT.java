package com.recipe.app.spring5recipeapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByDescription() {
		Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uom.get().getDescription());
	}
	
	@Test
	void testFindByDescriptionPinch() {
		Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Pinch");
		
		assertEquals("Pinch", uom.get().getDescription());
	}

}
