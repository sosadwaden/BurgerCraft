package com.sosadwaden.burgercraft.controller;

import com.sosadwaden.burgercraft.burger.BurgerOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid BurgerOrder order, Errors errors,
                               SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Заказ принят: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
