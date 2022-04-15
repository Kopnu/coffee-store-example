package love.korni.shopexample.config;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.dto.CoffeeDto;
import love.korni.shopexample.dto.CreateOrderDto;
import love.korni.shopexample.dto.OrderDto;
import love.korni.shopexample.dto.UserDto;

import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MapperConfig
 *
 * @author Sergei_Konilov
 */
@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(@NotNull MapperFactory mapperFactory) {
        mapperFactory.classMap(Coffee.class, CoffeeDto.class)
                .byDefault()
                .register();
        mapperFactory.classMap(Order.class, OrderDto.class)
                .byDefault()
                .customize(new OrderMapper())
                .register();
    }

    private static class OrderMapper extends CustomMapper<Order, OrderDto> {
        @Override
        public void mapAtoB(Order order, OrderDto orderDto, MappingContext context) {
            orderDto.setCustomer(new UserDto().setName(order.getUserName()));
            orderDto.setStatus(order.getStatus().name());
        }

        @Override
        public void mapBtoA(OrderDto orderDto, Order order, MappingContext context) {
            order.setStatus(Order.OrderStatus.valueOf(orderDto.getStatus()));
            order.setUserName(orderDto.getCustomer().getName());
        }
    }
}
