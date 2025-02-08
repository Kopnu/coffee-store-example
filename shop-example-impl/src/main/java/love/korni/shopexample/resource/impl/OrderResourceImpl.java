package love.korni.shopexample.resource.impl;

import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.dto.CreateOrderDto;
import love.korni.shopexample.dto.OrderDto;
import love.korni.shopexample.mapper.OrderMapper;
import love.korni.shopexample.resource.OrderResource;
import love.korni.shopexample.service.OrderService;
import love.korni.shopexample.service.SecurityService;
import love.korni.shopexample.util.BasicSecurityUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final SecurityService securityService;
    private final OrderMapper mapper;

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderDto create(CreateOrderDto createOrderDto) {
        securityService.checkIsUser();
        return mapper.toDto(orderService.create(createOrderDto.getCoffees()));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderDto getOrder(Long id) {
        securityService.checkIsUser();
        Order order = orderService.find(id);
        if (!checkOrderEligibility(order)) {
            return null;
        }
        return mapper.toDto(order);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<OrderDto> getOrders(String username) {
        securityService.checkIsUser();
        User user = BasicSecurityUtils.getUserFromContext();
        if (!user.getUsername().equals(username)) {
            return List.of();
        }
        return orderService.find(username).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDto> getOrders() {
        securityService.checkIsAdmin();
        return orderService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderDto confirmOrder(Long id) {
        securityService.checkIsUser();
        return mapper.toDto(orderService.confirm(id));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderDto update(OrderDto orderDto) {
        securityService.checkIsUser();
        Order order = mapper.toEntity(orderDto);
        return mapper.toDto(orderService.update(order));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
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
