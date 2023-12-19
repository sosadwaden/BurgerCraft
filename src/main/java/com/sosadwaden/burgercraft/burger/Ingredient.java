package com.sosadwaden.burgercraft.burger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Ingredient {

    @Id
    private String id;

    private String name;
    private IngredientType type;

    public enum IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
