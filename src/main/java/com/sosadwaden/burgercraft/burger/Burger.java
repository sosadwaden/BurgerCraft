package com.sosadwaden.burgercraft.burger;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Имя должно быть хотя 5 символов в длину")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    @ManyToMany()
    private List<Ingredient> ingredients = new ArrayList<>();

//    public void addIngredient(Ingredient ingredient) {
//        this.ingredients.add(new IngredientRef(ingredient.getId()));
//    }
}
