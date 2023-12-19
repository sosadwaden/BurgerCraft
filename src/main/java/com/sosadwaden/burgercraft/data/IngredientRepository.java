package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Optional<Ingredient> findById(String id);

    Iterable<Ingredient> findAll();

    Ingredient save(Ingredient ingredient);
}
