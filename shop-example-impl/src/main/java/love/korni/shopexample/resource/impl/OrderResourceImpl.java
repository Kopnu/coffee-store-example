package love.korni.shopexample.resource.impl;

import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.dto.CreateOrderDto;
import love.korni.shopexample.dto.OrderDto;
import love.korni.shopexample.resource.OrderResource;
import love.korni.shopexample.service.OrderService;
import love.korni.shopexample.service.SecurityService;
import love.korni.shopexample.util.BasicSecurityUtils;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * OrderResourceImpl
 *
 * @author Sergei_Konilov
 */
@RestController
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {

    private final OrderService orderService;
    private final MapperFacade mapperFacade;
    private final SecurityService securityService;

    @Override
    public OrderDto create(CreateOrderDto createOrderDto) {
        securityService.checkIsUser();
        return mapperFacade.map(orderService.create(createOrderDto.getCoffees()), OrderDto.class);
    }

    @Override
    public OrderDto getOrder(Long id) {
        securityService.checkIsUser();
        Order order = orderService.find(id);
        if (!checkOrderEligibility(order)) {
            return null;
        }
        return mapperFacade.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrders(String username) {
        securityService.checkIsUser();
        User user = BasicSecurityUtils.getUserFromContext();
        if (!user.getUsername().equals(username)) {
            return List.of();
        }
        return mapperFacade.mapAsList(orderService.find(username), OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrders() {
        securityService.checkIsAdmin();
        return mapperFacade.mapAsList(orderService.findAll(), OrderDto.class);
    }

    @Override
    public OrderDto confirmOrder(Long id) {
        securityService.checkIsUser();
        return mapperFacade.map(orderService.confirm(id), OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        securityService.checkIsUser();
        Order order = mapperFacade.map(orderDto, Order.class);
        return mapperFacade.map(orderService.update(order), OrderDto.class);
    }

    @Override
    public ResponseEntity<?> remove(Long id) {
        securityService.checkIsUser();
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    private boolean checkOrderEligibility(Order order) {
        User user = BasicSecurityUtils.getUserFromContext();
        return user.getUsername().equals(order.getUserName());
    }
}
