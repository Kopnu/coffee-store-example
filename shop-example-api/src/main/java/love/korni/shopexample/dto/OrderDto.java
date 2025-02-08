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

    @Schema(description = "Идентификатор заказа")
    private Long id;

    @Schema(description = "Список кофе")
    private List<CoffeeDto> coffees;

    @Schema(description = "Имя покупателя", example = "User")
    private String customer;

    @Schema(description = "Статус", example = "NEW")
    private OrderStatusDto status;
}
