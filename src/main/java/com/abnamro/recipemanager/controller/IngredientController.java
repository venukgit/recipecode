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

import com.abnamro.recipemanager.entity.IngredientEntity;
import com.abnamro.recipemanager.entity.dto.IngredientDto;
import com.abnamro.recipemanager.service.IngredientService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

 
/**
 * The Class IngredientController.
 */
@Api(value = "IngredientsController")
@RestController
@RequestMapping("/ingredients")
@Validated
@AllArgsConstructor
public class IngredientController {
	@Autowired
	private final IngredientService ingredientService;
 
	 
	/**
	 * Adds the ingredient.
	 *
	 * @param ingredientDTO the ingredient DTO
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<IngredientDto> addIngredient(@Valid @RequestBody final IngredientDto ingredientDTO) {
		return new ResponseEntity<>(
				IngredientDto.from(ingredientService.addIngredient(IngredientEntity.from(ingredientDTO))),
				HttpStatus.CREATED);
	}
	 
	 
	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	@GetMapping
	public ResponseEntity<List<IngredientDto>> getIngredients() {
		return new ResponseEntity<>(
				ingredientService.getIngredients().stream().map(IngredientDto::from).collect(Collectors.toList()),
				HttpStatus.OK);
	}
	 
	/**
	 * Gets the ingredient.
	 *
	 * @param id the id
	 * @return the ingredient
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<IngredientDto> getIngredient(@Valid @PathVariable final Long id) {
		return new ResponseEntity<>(IngredientDto.from(ingredientService.getIngredient(id)), HttpStatus.OK);
	}
	 
	/**
	 * Delete ingredient.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "{id}")
	public void deleteIngredient(@Valid @PathVariable final Long id) {
		ingredientService.deleteIngredient(id);
	}
	 
	/**
	 * Update ingredient.
	 *
	 * @param id the id
	 * @param ingredientDTO the ingredient DTO
	 * @return the response entity
	 */
	@PutMapping(value = "{id}")
	public ResponseEntity<IngredientDto> updateIngredient(@Valid @PathVariable final Long id,
			@Valid @RequestBody final IngredientDto ingredientDTO) {
		return new ResponseEntity<>(
				IngredientDto.from(ingredientService.update(id, IngredientEntity.from(ingredientDTO))),
				HttpStatus.ACCEPTED);
	}
}
