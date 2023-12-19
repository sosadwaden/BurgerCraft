package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
