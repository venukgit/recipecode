package com.abnamro.recipemanager.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import com.abnamro.recipemanager.entity.RecipeEntity;

import lombok.Data;

/**
 * The Class RecipeDto.
 */
@Data
public class RecipeDto {
	private Long recipeId;
	@NotEmpty(message="Recipe name cannot be empty or null")
	private String recipeName;
	private Boolean isVegetarian;
	@NotEmpty(message="Cooking instructions cannot be empty or null")
	private String cookingInstructions;
	private String noOfServings;
	private Date creationTime;
	private List<IngredientDto> ingredientsDto=new ArrayList<>();
	public static RecipeDto from(RecipeEntity recipeEntity) {
		RecipeDto recipeDto=new RecipeDto();
		recipeDto.setRecipeId(recipeEntity.getRecipeId());
		recipeDto.setRecipeName(recipeEntity.getRecipeName());
		recipeDto.setIsVegetarian(recipeEntity.getIsVegetarian());
		recipeDto.setCookingInstructions(recipeEntity.getCookingInstructions());
		recipeDto.setNoOfServings(recipeEntity.getNoOfServings());
		recipeDto.setCreationTime(recipeEntity.getCreationTime());
		recipeDto.setIngredientsDto(recipeEntity.getIngredientItems().stream().map(IngredientDto :: from)
				.collect(Collectors.toList()));
		return recipeDto;
	}
}
