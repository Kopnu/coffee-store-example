package love.korni.shopexample.resource;

import love.korni.shopexample.dto.CreateOrderDto;
import love.korni.shopexample.dto.OrderDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * OrderResource
 *
 * @author Sergei_Konilov
 */
@SecurityRequirement(name = "basicSecurity")
@RequestMapping("/api/v1/order")
public interface OrderResource {

    @Operation(summary = "Create new order")
    @PostMapping
    OrderDto create(@RequestBody CreateOrderDto createOrderDto);

    @Operation(summary = "Get order by id")
    @GetMapping("/{id}")
    OrderDto getOrder(@PathVariable Long id);

    @Operation(summary = "Get order for user by username")
    @GetMapping("/user/{username}")
    List<OrderDto> getOrders(@PathVariable String username);

    @Operation(summary = "Get list of orders")
    @GetMapping
    List<OrderDto> getOrders();

    @Operation(summary = "Confirm order")
    @GetMapping("/{id}/confirm")
    OrderDto confirmOrder(@PathVariable Long id);

    @Operation(summary = "Update order")
    @PutMapping
    OrderDto update(@RequestBody OrderDto orderDto);

    @Operation(summary = "Remove order by id")
    @DeleteMapping("/{id}")
    ResponseEntity<?> remove(@PathVariable Long id);

}
