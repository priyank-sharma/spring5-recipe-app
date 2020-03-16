package com.recipe.app.spring5recipeapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);
}
