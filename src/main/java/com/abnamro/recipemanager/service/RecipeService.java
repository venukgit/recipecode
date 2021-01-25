package com.abnamro.recipemanager.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnamro.recipemanager.entity.IngredientEntity;
import com.abnamro.recipemanager.entity.RecipeEntity;
import com.abnamro.recipemanager.entity.exception.RecipeNotFoundException;
import com.abnamro.recipemanager.repository.RecipeRepository;

import lombok.AllArgsConstructor;

 
@Service
@AllArgsConstructor
public class RecipeService {
	@Autowired
	private final RecipeRepository recipeRepository;
	@Autowired
	private final IngredientService ingredientService;

	/**
	 * Adds the recipe.
	 *
	 * @param recipeEntity the recipe entity
	 * @return the recipe entity
	 */
	public RecipeEntity addRecipe(RecipeEntity recipeEntity) {
		return recipeRepository.save(recipeEntity);
	}

	/**
	 * Gets the recipes.
	 *
	 * @return the recipes
	 */
	public List<RecipeEntity> getRecipes() {
		return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	/**
	 * Gets the recipe by id.
	 *
	 * @param id the id
	 * @return the recipe by id
	 */
	public RecipeEntity getRecipeById(Long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
	}
   

	/**
	 * Delete recipe.
	 *
	 * @param id the id
	 * @return the recipe entity
	 */
	public void deleteRecipe(Long id) {
		RecipeEntity recipeEntity = getRecipeById(id);
		recipeRepository.delete(recipeEntity);
	}

	/**
	 * Edits the recipe.
	 *
	 * @param id the id
	 * @param entity the entity
	 * @return the recipe entity
	 */
	@Transactional
	public RecipeEntity updateRecipe(Long id, RecipeEntity entity) {
		RecipeEntity recipeEntityToUpdate = getRecipeById(id);
		recipeEntityToUpdate.setRecipeName(entity.getRecipeName());
		recipeEntityToUpdate.setIsVegetarian(entity.getIsVegetarian());
		recipeEntityToUpdate.setCookingInstructions(entity.getCookingInstructions());
		recipeEntityToUpdate.setNoOfServings(entity.getNoOfServings());
		recipeEntityToUpdate.setCreationTime(entity.getCreationTime());
		recipeEntityToUpdate.setIngredientItems(entity.getIngredientItems());
		return recipeEntityToUpdate;
	}

	/**
	 * Adds the ingredient to recipe.
	 *
	 * @param recipeId the recipe id
	 * @param ingredientId the ingredient id
	 * @return the recipe entity
	 */
	@Transactional
	public RecipeEntity addIngredientToRecipe(Long recipeId, Long ingredientId) {
		RecipeEntity recipeEntity = getRecipeById(recipeId);
		IngredientEntity ingredientEntity = ingredientService.getIngredient(ingredientId);
		recipeEntity.addRecipe(ingredientEntity);
		return recipeEntity;
	}

	/**
	 * Removes the ingredient from recipe.
	 *
	 * @param recipeId the recipe id
	 * @param ingredientId the ingredient id
	 * @return the recipe entity
	 */
	@Transactional
	public RecipeEntity removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
		RecipeEntity recipeEntity = getRecipeById(recipeId);
		IngredientEntity ingredientEntity = ingredientService.getIngredient(ingredientId);
		recipeEntity.removeIngredient(ingredientEntity);
		return recipeEntity;
	}
}
