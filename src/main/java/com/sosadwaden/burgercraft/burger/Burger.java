package com.sosadwaden.burgercraft.burger;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Burger {

    @NotNull
    @Size(min = 5, message = "Имя должно быть хотя 5 символов в длину")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    private List<Ingredient> ingredients;
}