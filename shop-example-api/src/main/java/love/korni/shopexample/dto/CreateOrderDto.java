package love.korni.shopexample.dto;

import lombok.Data;

import java.util.List;

/**
 * CreateOrderDto
 *
 * @author Sergei_Konilov
 */
@Data
public class CreateOrderDto {
    private List<Long> coffees;
}
