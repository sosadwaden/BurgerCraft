package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.Burger;
import com.sosadwaden.burgercraft.burger.BurgerOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<BurgerOrder, Long> {

    List<BurgerOrder> findByDeliveryZip(String deliveryZip);

    List<BurgerOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

//    @Query("Order o where o.deliveryCity='Seattle'")
//    List<BurgerOrder> readOrdersDeliveredInSeattle();
}
