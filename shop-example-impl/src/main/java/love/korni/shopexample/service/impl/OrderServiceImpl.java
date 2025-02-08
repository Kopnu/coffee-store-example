package love.korni.shopexample.service.impl;

import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.domain.enums.OrderStatus;
import love.korni.shopexample.exception.EntityNotFoundException;
import love.korni.shopexample.exception.ValidationException;
import love.korni.shopexample.payment.PaymentService;
import love.korni.shopexample.repository.OrderRepository;
import love.korni.shopexample.service.CoffeeService;
import love.korni.shopexample.service.OrderService;
import love.korni.shopexample.util.BasicSecurityUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderServiceImpl
 *
 * @author Sergei_Konilov
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeService coffeeService;
    private final PaymentService paymentService;

    @Override
    public Order create(List<Long> coffeeIds) {
        if (coffeeIds.isEmpty()) throw new ValidationException("Order cannot be empty. Add some coffee.");
        Order order = new Order();
        order.setCoffees(coffeeService.findAll(coffeeIds));
        order.setUserName(BasicSecurityUtils.getUserFromContext().getUsername());
        return orderRepository.save(order);
    }

    @Override
    public Order find(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (OrderStatus.DELETED == order.getStatus()) {
            return null;
        }
        return order;
    }

    @Override
    public List<Order> find(String username) {
        List<Order> orders = orderRepository.findByUserName(username);
        return orders.stream().filter(order -> OrderStatus.DELETED != order.getStatus()).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(order -> OrderStatus.DELETED != order.getStatus()).collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        find(order.getId());
        if (order.getCoffees().isEmpty()) throw new ValidationException("Order cannot be empty. Add some coffee.");
        if (OrderStatus.NEW != order.getStatus()) throw new ValidationException("You cannot update confirmed orders");
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = find(id);
        if (OrderStatus.NEW != order.getStatus() || OrderStatus.WAIT_PAYMENT != order.getStatus())
            throw new ValidationException("You cannot delete a paid order");
        order.setStatus(OrderStatus.DELETED);
        orderRepository.save(order);
    }

    @Override
    public Order confirm(Long id) {
        Order order = find(id);
        if (OrderStatus.NEW != order.getStatus()) throw new ValidationException("OrderStatus must be NEW");
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        Order save = orderRepository.save(order);
        paymentService.payment(save);
        return save;
    }

}
