package com.abnamro.recipemanager.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.abnamro.recipemanager.entity.dto.RecipeDto;

import lombok.Data;

/**
 * The Class RecipeEntity.
 */
@Data
@Entity
@Table(name = "RecipeEntity")
public class RecipeEntity {
	@Id
	@GeneratedValue
	private Long recipeId;
	
	private String recipeName;
	private Boolean isVegetarian;
	private String cookingInstructions;
	private String noOfServings;
	private Date creationTime;
	@OneToMany(
			cascade = CascadeType.ALL
			)
	@JoinColumn(name="recipe_id")
	private List<IngredientEntity> ingredientItems=new ArrayList<>();	 
	public void addRecipe(IngredientEntity ingredient) {
		ingredientItems.add(ingredient);
	}
	public void removeIngredient(IngredientEntity ingredient) {
		ingredientItems.remove(ingredient);
	}
	public static RecipeEntity from(RecipeDto recipeDto) {
		RecipeEntity recipeEntity=new RecipeEntity();
		recipeEntity.setRecipeName(recipeDto.getRecipeName());
		recipeEntity.setIsVegetarian(recipeDto.getIsVegetarian());
		recipeEntity.setCookingInstructions(recipeDto.getCookingInstructions());
		recipeEntity.setNoOfServings(recipeDto.getNoOfServings());
		recipeEntity.setCreationTime(recipeDto.getCreationTime());
		recipeEntity.setIngredientItems(recipeDto.getIngredientsDto().stream().map(IngredientEntity :: from).collect(Collectors.toList()));
		return recipeEntity;
	}
}
