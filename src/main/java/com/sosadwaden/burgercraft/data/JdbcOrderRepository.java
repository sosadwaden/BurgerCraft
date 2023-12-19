package com.sosadwaden.burgercraft.data;

import com.sosadwaden.burgercraft.burger.Burger;
import com.sosadwaden.burgercraft.burger.BurgerOrder;
import com.sosadwaden.burgercraft.burger.IngredientRef;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public BurgerOrder save(BurgerOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                """
                    insert into Burger_Order (delivery_name, delivery_street, delivery_city,
                    delivery_state, delivery_zip, cc_number,
                    cc_expiration, cc_cvv, placed_at)
                    values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt())
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Burger> burgers = order.getBurgers();
        int i = 0;
        for (Burger burger: burgers) {
            saveBurger(orderId, i++, burger);
        }

        return order;
    }

    private long saveBurger(Long orderId, int orderKey, Burger burger) {
        burger.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        """
                            insert into Burger 
                            (name, created_at, burger_order, burger_order_key)
                            values (?, ?, ?, ?)
                            """,
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                burger.getName(),
                                burger.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long burgerId = keyHolder.getKey().longValue();
        burger.setId(burgerId);

        saveIngredientRefs(burgerId, burger.getIngredients());

        return burgerId;
    }

    private void saveIngredientRefs(long burgerId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    """
                        insert into Ingredient_Ref (ingredient, burger, burger_key)
                        values (?, ?, ?) 
                        """,
                    ingredientRef.getIngredient(), burgerId, key++);
        }
    }
}
