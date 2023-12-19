package com.sosadwaden.burgercraft;

import com.sosadwaden.burgercraft.burger.Ingredient;
import com.sosadwaden.burgercraft.data.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BurgerCraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerCraftApplication.class, args);
    }

    // bean для загрузки данных в БД
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.save(new Ingredient("BLBR", "Чёрный хлеб", Ingredient.IngredientType.WRAP));
            repository.save(new Ingredient("WHBR", "Белый хлеб", Ingredient.IngredientType.WRAP));
            repository.save(new Ingredient("CHIC", "Курица", Ingredient.IngredientType.PROTEIN));
            repository.save(new Ingredient("GRBF", "Говяжий фарш", Ingredient.IngredientType.PROTEIN));
            repository.save(new Ingredient("TMTO", "Помидоры", Ingredient.IngredientType.VEGGIES));
            repository.save(new Ingredient("LETC", "Салат", Ingredient.IngredientType.VEGGIES));
            repository.save(new Ingredient("CHED", "Чеддер", Ingredient.IngredientType.CHEESE));
            repository.save(new Ingredient("KETC", "Кетчуп", Ingredient.IngredientType.SAUCE));
            repository.save(new Ingredient("SLSA", "Салься", Ingredient.IngredientType.SAUCE));
        };
    }

}
