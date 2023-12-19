package com.sosadwaden.burgercraft.controller;

import com.sosadwaden.burgercraft.burger.Burger;
import com.sosadwaden.burgercraft.burger.BurgerOrder;
import com.sosadwaden.burgercraft.burger.Ingredient;
import com.sosadwaden.burgercraft.burger.Ingredient.IngredientType;
import com.sosadwaden.burgercraft.data.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignBurgerController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));

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

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {
        return ingredients.stream()
                .filter(in -> in.getType().equals(type))
                .collect(Collectors.toList());
    }

}
