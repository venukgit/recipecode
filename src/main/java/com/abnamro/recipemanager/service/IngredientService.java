package com.abnamro.recipemanager.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnamro.recipemanager.entity.IngredientEntity;
import com.abnamro.recipemanager.entity.exception.IngredientNotFoundException;
import com.abnamro.recipemanager.repository.IngredientRepository;

import lombok.AllArgsConstructor;
 
/**
 * The Class IngredientService.
 */
@Service
@AllArgsConstructor
public class IngredientService {
	@Autowired
	private final IngredientRepository ingredientRepository;
 

	/**
	 * Adds the ingredient.
	 *
	 * @param ingredientEntity the ingredient entity
	 * @return the ingredient entity
	 */
	public IngredientEntity addIngredient(IngredientEntity ingredientEntity) {
		return ingredientRepository.save(ingredientEntity);
	}

	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	public List<IngredientEntity> getIngredients() {
		return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public IngredientEntity getIngredient(Long id) {
		return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
	}

	/**
	 * Delete ingredient.
	 *
	 * @param id the id
	 */
	public void deleteIngredient(Long id) {
		IngredientEntity IngredientEntity = getIngredient(id);
		ingredientRepository.delete(IngredientEntity);
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param ingredientEntity the ingredient entity
	 * @return the ingredient entity
	 */
	@Transactional
	public IngredientEntity update(Long id, IngredientEntity ingredientEntity) {
		IngredientEntity ingredientEntityToUpdate = getIngredient(id);
		ingredientEntityToUpdate.setIngredientName(ingredientEntity.getIngredientName());
		ingredientEntityToUpdate.setQuantity(ingredientEntity.getQuantity());
		ingredientEntityToUpdate.setMeasure(ingredientEntity.getMeasure());
		return ingredientEntityToUpdate;
	}
}
