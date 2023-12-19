package com.sosadwaden.burgercraft.burger;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class BurgerOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message = "Имя заказа обязательно")
    private String deliveryName;

    @NotBlank(message = "Улица обязательна")
    private String deliveryStreet;

    @NotBlank(message = "Город обязателен")
    private String deliveryCity;

    @NotBlank(message = "Область обязательна")
    private String deliveryState;

    @NotBlank(message = "Зип код обязателен")
    private String deliveryZip;

    @CreditCardNumber(message = "Невалидный номер карты") // Используется алгоритм Луна
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$",
             message = "Должен быть формат ММ/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Неверный CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        this.burgers.add(burger);
    }

}
