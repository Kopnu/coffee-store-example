package love.korni.shopexample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Order status", example = "NEW")
    private String status;
}
