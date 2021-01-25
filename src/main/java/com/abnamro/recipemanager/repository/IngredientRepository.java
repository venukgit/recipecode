package com.abnamro.recipemanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abnamro.recipemanager.entity.IngredientEntity;

/**
 * The Interface IngredientRepository.
 */
@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, Long> {

}
