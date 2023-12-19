package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.BurgerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<BurgerOrder, Long> {
}
