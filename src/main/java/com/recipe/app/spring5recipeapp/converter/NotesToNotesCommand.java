package com.recipe.app.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.app.spring5recipeapp.command.NotesCommand;
import com.recipe.app.spring5recipeapp.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source) {
		
		if(source == null) {
			return null;
		}
		
		final NotesCommand notesCommand = new NotesCommand();
		
		notesCommand.setId(source.getId());
		notesCommand.setNotes(source.getNotes());
		
		return notesCommand;
	}

}
