package com.abnamro.recipemanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.recipemanager.entity.RecipeEntity;
import com.abnamro.recipemanager.entity.dto.RecipeDto;
import com.abnamro.recipemanager.service.RecipeService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Api(value = "RecipeController")
@RestController
@RequestMapping("/recipes")
@Validated
@AllArgsConstructor
public class RecipeController {
	@Autowired
	private RecipeService recipeService;

	/**
	 * Adds the recipe.
	 *
	 * @param recipeDto the recipe dto
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<RecipeDto> addRecipe(@Valid @RequestBody final RecipeDto recipeDto) {
		return new ResponseEntity<>(RecipeDto.from(recipeService.addRecipe(RecipeEntity.from(recipeDto))),
				HttpStatus.CREATED);
	}

	/**
	 * Gets the recipes.
	 *
	 * @return the recipes
	 */
	@GetMapping
	public ResponseEntity<List<RecipeDto>> getRecipes() {
		return new ResponseEntity<>(recipeService.getRecipes().stream().map(RecipeDto::from).collect(Collectors.toList()),
				HttpStatus.OK);
	}
   
	/**
	 * Gets the recipe by id.
	 *
	 * @param id the id
	 * @return the recipe by id
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<RecipeDto> getRecipeById(@Valid @PathVariable final Long id) {
		return new ResponseEntity<>(RecipeDto.from(recipeService.getRecipeById(id)), HttpStatus.OK);
	}
 
	/**
	 * Delete recipe by id.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "{id}")
	public void deleteRecipeById(@Valid @PathVariable final Long id) {
		recipeService.deleteRecipe(id);
	}

	/**
	 * Edits the recipe by id.
	 *
	 * @param id the id
	 * @param recipeDto the recipe dto
	 * @return the response entity
	 */
	@PutMapping(value = "{id}")
	public ResponseEntity<RecipeDto> updateRecipeById(@Valid @PathVariable final Long id,
			@Valid @RequestBody final RecipeDto recipeDto) {
		return new ResponseEntity<>(RecipeDto.from(recipeService.updateRecipe(id, RecipeEntity.from(recipeDto))),
				HttpStatus.OK);
	}

	/**
	 * Adds the ingredient to recipe.
	 *
	 * @param recipeId the recipe id
	 * @param ingredientId the ingredient id
	 * @return the response entity
	 */
	@PostMapping(value = "{recipeId}/ingredients/{ingredientId}")
	public ResponseEntity<RecipeDto> addIngredientToRecipe(@Valid @PathVariable final Long recipeId,
			@Valid @PathVariable final Long ingredientId) {
		return new ResponseEntity<>(RecipeDto.from(recipeService.addIngredientToRecipe(recipeId, ingredientId)),
				HttpStatus.OK);
	}

	/**
	 * Removes the ingredient from recipe.
	 *
	 * @param recipeId the recipe id
	 * @param ingredientId the ingredient id
	 * @return the response entity
	 */
	@DeleteMapping(value = "{recipeId}/ingredients/{ingredientId}")
	public ResponseEntity<RecipeDto> removeIngredientFromRecipe(@Valid @PathVariable final Long recipeId,
			@Valid @PathVariable final Long ingredientId) {
		return new ResponseEntity<>(RecipeDto.from(recipeService.removeIngredientFromRecipe(recipeId, ingredientId)),
				HttpStatus.OK);
	}
}
