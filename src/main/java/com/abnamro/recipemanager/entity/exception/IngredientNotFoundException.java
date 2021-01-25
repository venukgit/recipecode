package com.abnamro.recipemanager.entity.exception;

import java.text.MessageFormat;

/**
 * The Class IngredientNotFoundException.
 */
public class IngredientNotFoundException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
 * Instantiates a new ingredient not found exception.
 *
 * @param id the id
 */
public IngredientNotFoundException(Long id) {
	super(MessageFormat.format("Could not find Ingredient with id:{}", id));
}
}
