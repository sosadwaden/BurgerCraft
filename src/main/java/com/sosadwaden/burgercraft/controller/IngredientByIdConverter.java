package com.sosadwaden.burgercraft.controller;

import com.sosadwaden.burgercraft.burger.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("BLBR", new Ingredient("BLBR", "Чёрный хлеб", Ingredient.IngredientType.WRAP));
        ingredientMap.put("WHBR", new Ingredient("WHBR", "Белый хлеб", Ingredient.IngredientType.WRAP));
        ingredientMap.put("CHIC", new Ingredient("CHIC", "Курица", Ingredient.IngredientType.PROTEIN));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Говяжий фарш", Ingredient.IngredientType.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Помидоры", Ingredient.IngredientType.VEGGIES));
        ingredientMap.put("LETC", new Ingredient("LETC", "Салат", Ingredient.IngredientType.VEGGIES));
        ingredientMap.put("CHED",new Ingredient("CHED", "Чеддер", Ingredient.IngredientType.CHEESE));
        ingredientMap.put("KETC",new Ingredient("KETC", "Кетчуп", Ingredient.IngredientType.SAUCE));
        ingredientMap.put("SLSA",new Ingredient("SLSA", "Салься", Ingredient.IngredientType.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }

}
