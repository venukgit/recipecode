package com.abnamro.recipemanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.abnamro.recipemanager.entity.dto.IngredientDto;
import com.abnamro.recipemanager.entity.dto.RecipeDto;

import lombok.Data;

/**
 * The Class IngredientEntity.
 */
@Data
@Entity
@Table(name= "IngredientEntity")
public class IngredientEntity {
	@Id
	@GeneratedValue
	private Long ingredientId;
	private String ingredientName;
	private Integer quantity;
	private String measure;
	@ManyToOne
	private RecipeEntity entity;
	public static IngredientEntity from(IngredientDto ingredientDto) {
		IngredientEntity ingredientEntity=new IngredientEntity();
		ingredientEntity.setIngredientName(ingredientDto.getIngredientName());
		ingredientEntity.setQuantity(ingredientDto.getQuantity());
		ingredientEntity.setMeasure(ingredientDto.getMeasure());
		return ingredientEntity;
	}
}
