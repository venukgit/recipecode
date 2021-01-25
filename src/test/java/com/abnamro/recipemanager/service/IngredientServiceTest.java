package com.abnamro.recipemanager.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.abnamro.recipemanager.entity.IngredientEntity;
import com.abnamro.recipemanager.repository.IngredientRepository;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WithMockUser(username = "VenuGopal", password = "password", roles = "USER")
public class IngredientServiceTest {
	@Autowired
	IngredientService ingredientService;
	@Mock
	IngredientRepository ingredientRepository;
	
	@Test
	public void TestAddIngredient() {
		IngredientEntity ingredientEntity=new IngredientEntity();
		ingredientEntity.setIngredientName("sugar");
		ingredientEntity.setMeasure("grams");
		ingredientEntity.setQuantity(100);
		when(ingredientRepository.save(ingredientEntity)).thenReturn(ingredientEntity);
		assertEquals(ingredientEntity, ingredientService.addIngredient(ingredientEntity));
	}
}
