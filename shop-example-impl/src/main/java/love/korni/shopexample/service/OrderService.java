package love.korni.shopexample.service;

import love.korni.shopexample.domain.entity.Order;

import java.util.List;

/**
 * OrderService
 *
 * @author Sergei_Konilov
 */
public interface OrderService {
    Order create(List<Long> coffeeIds);

    Order find(Long id);

    List<Order> find(String username);

    List<Order> findAll();

    Order update(Order order);

    void delete(Long id);

    Order confirm(Long id);
}
