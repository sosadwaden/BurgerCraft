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
       Iterable<Ingredient> ingredients = ingredientRepository.findAll();
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
        // log.info("Приготовление бургера: {}", burger);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, IngredientType type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(it -> it.getType().equals(type))
                .collect(Collectors.toList());
    }

}
