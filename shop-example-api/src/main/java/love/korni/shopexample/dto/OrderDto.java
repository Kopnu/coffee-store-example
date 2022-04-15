package love.korni.shopexample.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * OrderDto
 *
 * @author Sergei_Konilov
 */
@Data
@Accessors(chain = true)
public class OrderDto {

    private Long id;

    private List<CoffeeDto> coffees;

    private UserDto customer;

    private String status;
}
