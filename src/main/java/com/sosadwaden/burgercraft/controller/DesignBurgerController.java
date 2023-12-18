package com.sosadwaden.burgercraft.controller;

import com.sosadwaden.burgercraft.burger.Burger;
import com.sosadwaden.burgercraft.burger.BurgerOrder;
import com.sosadwaden.burgercraft.burger.Ingredient;
import com.sosadwaden.burgercraft.burger.Ingredient.IngredientType;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("BLBR", "Чёрный хлеб", IngredientType.WRAP),
                new Ingredient("WHBR", "Белый хлеб", IngredientType.WRAP),
                new Ingredient("CHIC", "Курица", IngredientType.PROTEIN),
                new Ingredient("GRBF", "Говяжий фарш", IngredientType.PROTEIN),
                new Ingredient("TMTO", "Помидоры", IngredientType.VEGGIES),
                new Ingredient("LETC", "Салат", IngredientType.VEGGIES),
                new Ingredient("CHED", "Чеддер", IngredientType.CHEESE),
                //new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                new Ingredient("KETC", "Кетчуп", IngredientType.SAUCE),
                new Ingredient("SLSA", "Салься", IngredientType.SAUCE)
        );

        IngredientType[] types = IngredientType.values();
        for (IngredientType type: types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "burgerOrder")
    public BurgerOrder order() {
        return new BurgerOrder();
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processBurger(@Valid Burger burger, Errors errors,
                                @ModelAttribute BurgerOrder burgerOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        burgerOrder.addBurger(burger);
        log.info("Приготовление бургера: {}", burger);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
        return ingredients.stream()
                .filter(it -> it.getType().equals(type))
                .collect(Collectors.toList());
    }

}
