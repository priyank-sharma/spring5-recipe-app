package com.recipe.app.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipe.app.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
