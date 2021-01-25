package com.abnamro.recipemanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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

import com.abnamro.recipemanager.entity.IngredientEntity;
import com.abnamro.recipemanager.service.IngredientService;
import com.google.gson.reflect.TypeToken;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IngredientController.class)
@WithMockUser(username = "VenuGopal", password = "password", roles = "USER")
public class IngredientControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	IngredientService ingredientService;
	private final String URL = "/ingredients/";

	@Test
	public void testAddIngredient() throws Exception {
		IngredientEntity ingredientStub = new IngredientEntity();
		ingredientStub.setIngredientId(1l);
		ingredientStub.setIngredientName("sugar");
		ingredientStub.setQuantity(1);
		ingredientStub.setMeasure("kg");
		when(ingredientService.addIngredient(any(IngredientEntity.class))).thenReturn(ingredientStub);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(ingredientStub)))
				.andReturn();
		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(ingredientService).addIngredient(any(IngredientEntity.class));
		IngredientEntity resultIngredient = TestUtils.jsonToObject(result.getResponse().getContentAsString(),
				IngredientEntity.class);
		assertNotNull(resultIngredient);
		assertEquals(1l, resultIngredient.getIngredientId().longValue());
	}

	@Test
	public void testGetIngredients() throws Exception {

		// prepare data and mock's behaviour
		List<IngredientEntity> ingredientList = buildIngredients();
		when(ingredientService.getIngredients()).thenReturn(ingredientList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(ingredientService).getIngredients();

		// get the List<IngredientEntity> from the Json response
		TypeToken<List<IngredientEntity>> token = new TypeToken<List<IngredientEntity>>() {
		};
		@SuppressWarnings("unchecked")
		List<IngredientEntity> ingredientListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(),
				token);

		assertNotNull(ingredientListResult, "Ingredients not found");
		assertEquals(ingredientList.size(), ingredientListResult.size(), "Incorrect ingredient List");

	}

	@Test
	public void testGetIngredient() throws Exception {
		IngredientEntity ingredientStub = new IngredientEntity();
		ingredientStub.setIngredientId(1l);
		ingredientStub.setIngredientName("sugar");
		ingredientStub.setQuantity(1);
		ingredientStub.setMeasure("kg");
		
		// prepare data and mock's behaviour
		when(ingredientService.getIngredient(ingredientStub.getIngredientId())).thenReturn(ingredientStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + ingredientStub.getIngredientId())
				.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

		// verify that service method was called once
		verify(ingredientService).getIngredient(ingredientStub.getIngredientId());

		// get the List<IngredientEntity> from the Json response
		TypeToken<IngredientEntity> token = new TypeToken<IngredientEntity>() {
		};
		IngredientEntity ingredientListResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(),
				IngredientEntity.class, token);

		assertNotNull(ingredientListResult, "Ingredients not found");
		assertEquals(ingredientStub, ingredientListResult, "Incorrect ingredient List");

	}

	private List<IngredientEntity> buildIngredients() {
		IngredientEntity ingredient1 = new IngredientEntity();
		ingredient1.setIngredientId(1l);
		ingredient1.setIngredientName("sugar");
		ingredient1.setQuantity(1);
		ingredient1.setMeasure("kg");
		IngredientEntity ingredient2 = new IngredientEntity();
		ingredient2.setIngredientId(2l);
		ingredient2.setIngredientName("water");
		ingredient2.setQuantity(1);
		ingredient2.setMeasure("litre");
		List<IngredientEntity> ingredientList = Arrays.asList(ingredient1, ingredient2);
		return ingredientList;
	}
}
