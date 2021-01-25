package com.abnamro.recipemanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abnamro.recipemanager.entity.RecipeEntity;
import com.abnamro.recipemanager.service.RecipeService;
import com.google.gson.reflect.TypeToken;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
@WithMockUser(username = "VenuGopal", password = "password", roles = "USER")
public class RecipeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RecipeService recipeService;

	private final String URL = "/recipes/";
	
	@Test

	public void testAddRecipe() throws Exception {
		RecipeEntity recipeStub = new RecipeEntity();
		recipeStub.setRecipeId(3l);
		;
		recipeStub.setRecipeName("sweet");
		recipeStub.setNoOfServings("5-6");
		recipeStub.setIsVegetarian(true);
		recipeStub.setCookingInstructions("mix");
		when(recipeService.addRecipe((any(RecipeEntity.class)))).thenReturn(recipeStub);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(recipeStub)))
				.andReturn();
		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(recipeService).addRecipe(any(RecipeEntity.class));
		RecipeEntity resultRecipe = TestUtils.jsonToObject(result.getResponse().getContentAsString(),
				RecipeEntity.class);
		assertNotNull(resultRecipe);
		assertEquals(3l, resultRecipe.getRecipeId().longValue());
	}

	@Test
	public void testGetRecipes() throws Exception {

		// prepare data and mock's behaviour
		List<RecipeEntity> recipesList = buildRecipes();
		when(recipeService.getRecipes()).thenReturn(recipesList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(recipeService).getRecipes();

		// get the List<RecipeEntity> from the Json response
		TypeToken<List<RecipeEntity>> token = new TypeToken<List<RecipeEntity>>() {
		};
		@SuppressWarnings("unchecked")
		List<RecipeEntity> recipeListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);

		assertNotNull(recipeListResult, "Recipes not found");
		assertEquals(recipesList.size(), recipeListResult.size(), "Incorrect Recipe List");

	}

	@Test
	public void testGetRecipeById() throws Exception {
		RecipeEntity recipeStub = new RecipeEntity();
		recipeStub.setRecipeId(3l);
		;
		recipeStub.setRecipeName("sweet");
		recipeStub.setNoOfServings("5-6");
		recipeStub.setIsVegetarian(true);
		recipeStub.setCookingInstructions("mix");
		// prepare data and mock's behaviour
		when(recipeService.getRecipeById(recipeStub.getRecipeId())).thenReturn(recipeStub);

		// execute
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(URL + recipeStub.getRecipeId()).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(recipeService).getRecipeById(recipeStub.getRecipeId());

		// get the List<IngredientEntity> from the Json response
		TypeToken<RecipeEntity> token = new TypeToken<RecipeEntity>() {
		};
		RecipeEntity recipeListResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(),
				RecipeEntity.class, token);

		assertNotNull(recipeListResult, "Recipes not found");
		assertEquals(recipeStub, recipeListResult, "Incorrect ingredient List");

	}

	private List<RecipeEntity> buildRecipes() {
		RecipeEntity recipeEntity1 = new RecipeEntity();
		recipeEntity1.setRecipeId(4l);
		recipeEntity1.setRecipeName("Sweet");
		recipeEntity1.setNoOfServings("4-5");
		recipeEntity1.setIsVegetarian(true);
		recipeEntity1.setCreationTime(new Date());
		recipeEntity1.setCookingInstructions("boil");
		RecipeEntity recipeEntity2 = new RecipeEntity();
		recipeEntity2.setRecipeId(4l);
		recipeEntity2.setRecipeName("Chicken Biriyani");
		recipeEntity2.setNoOfServings("1-2");
		recipeEntity2.setIsVegetarian(false);
		recipeEntity2.setCreationTime(new Date());
		recipeEntity2.setCookingInstructions("boil");
		List<RecipeEntity> recipeEntitytList = Arrays.asList(recipeEntity1, recipeEntity2);
		return recipeEntitytList;
	}

}
