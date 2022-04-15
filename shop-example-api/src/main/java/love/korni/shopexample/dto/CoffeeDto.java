package love.korni.shopexample.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * CoffeeDto
 *
 * @author Sergei_Konilov
 */
@Data
@Accessors(chain = true)
public class CoffeeDto {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String coffeeName;

    private Double cost;

}
