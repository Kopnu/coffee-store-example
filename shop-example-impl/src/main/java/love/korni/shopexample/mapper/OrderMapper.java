package love.korni.shopexample.mapper;

import love.korni.shopexample.domain.entity.Order;
import love.korni.shopexample.dto.OrderDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sergei_Kornilov
 */
@Mapper(uses = {
        CoffeeMapper.class
})
public interface OrderMapper {

    @Mapping(target = "customer", source = "userName")
    OrderDto toDto(Order order);

    @Mapping(target = "userName", source = "customer")
    Order toEntity(OrderDto orderDto);

}
