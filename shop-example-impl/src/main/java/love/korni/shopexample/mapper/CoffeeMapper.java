package love.korni.shopexample.mapper;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.dto.CoffeeDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Sergei_Kornilov
 */
@Mapper
public interface CoffeeMapper {

    CoffeeDto toDto(Coffee coffee);

    @Mapping(target = "img", ignore = true)
    Coffee toEntity(CoffeeDto coffeeDto);

}
