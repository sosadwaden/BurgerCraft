package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.BurgerOrder;

public interface OrderRepository {
    BurgerOrder save(BurgerOrder order);
}
