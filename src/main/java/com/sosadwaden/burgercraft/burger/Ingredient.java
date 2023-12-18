package com.sosadwaden.burgercraft.burger;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;

    public enum IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
