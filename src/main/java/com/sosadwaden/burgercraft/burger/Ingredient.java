package com.sosadwaden.burgercraft.burger;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient implements Persistable<String>  {

    @Id
    private String id;

    private String name;
    private IngredientType type;

    @Override
    public boolean isNew() {
        return true;
    }

    public enum IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
