package love.korni.shopexample.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CoffeeDto
 *
 * @author Sergei_Konilov
 */
@Data
@Accessors(chain = true)
public class CoffeeDto {

    private Long id;

    private String coffeeName;

    private Double cost;

}
