package com.abnamro.recipemanager.entity.dto;

import javax.validation.constraints.NotEmpty;

import com.abnamro.recipemanager.entity.IngredientEntity;

import lombok.Data;

/**
 * The Class IngredientDto.
 */
@Data
public class IngredientDto {
	
	private Long ingredientId;
	@NotEmpty(message="Ingredient name cannot be empty or null")
	private String ingredientName;
	private Integer quantity;
	@NotEmpty(message="Ingredient measure cannot be empty or null")
	private String measure;
	public static IngredientDto from(IngredientEntity ingredientEntity) {
		IngredientDto ingredientDto=new IngredientDto();
		ingredientDto.setIngredientId(ingredientEntity.getIngredientId());
		ingredientDto.setIngredientName(ingredientEntity.getIngredientName());
		ingredientDto.setQuantity(ingredientEntity.getQuantity());
		ingredientDto.setMeasure(ingredientEntity.getMeasure());
		
		return ingredientDto;
	}
}
