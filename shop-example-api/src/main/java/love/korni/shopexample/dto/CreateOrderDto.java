package love.korni.shopexample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * CreateOrderDto
 *
 * @author Sergei_Konilov
 */
@Data
public class CreateOrderDto {
    @Schema(description = "List of coffee ids", example = "[1,2]")
    private List<Long> coffees;
}
