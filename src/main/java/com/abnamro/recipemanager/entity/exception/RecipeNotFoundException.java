package com.abnamro.recipemanager.entity.exception;

import java.text.MessageFormat;

public class RecipeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecipeNotFoundException(Long id) {
		super(MessageFormat.format("Could not find Recipe with id:{}", id));
	}
}
