package com.recipe.app.spring5recipeapp.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.recipe.app.spring5recipeapp.command.UnitOfMeasureCommand;
import com.recipe.app.spring5recipeapp.converter.UOMToUomCommand;
import com.recipe.app.spring5recipeapp.repository.UnitOfMeasureRepository;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

	private UnitOfMeasureRepository unitOfMeasureRepository;
	private UOMToUomCommand uomToUomCommand;
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UOMToUomCommand uomToUomCommand) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.uomToUomCommand = uomToUomCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> listAllUOMs() {
		// TODO Auto-generated method stub
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
				.map(uomToUomCommand::convert)
				.collect(Collectors.toSet());
	}

}
