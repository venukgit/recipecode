package com.abnamro.recipemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abnamro.recipemanager.entity.RecipeEntity;

/**
 * The Interface RecipeRepository.
 */
@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long>{
	
}
