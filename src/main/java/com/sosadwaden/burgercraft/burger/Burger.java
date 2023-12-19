package com.sosadwaden.burgercraft.burger;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
@EqualsAndHashCode(exclude = "createdAt")
public class Burger {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Имя должно быть хотя 5 символов в длину")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    private List<IngredientRef> ingredients = new ArrayList<>();

//    public void addIngredient(Ingredient ingredient) {
//        this.ingredients.add(new IngredientRef(ingredient.getId()));
//    }
}
