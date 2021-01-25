package com.abnamro.recipemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The Class RecipeManagerApplication.
 */
@SpringBootApplication
public class RecipeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeManagerApplication.class, args);
	}

}
