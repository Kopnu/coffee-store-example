package love.korni.shopexample.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserDto
 *
 * @author Sergei_Konilov
 */
@Data
@Accessors(chain = true)
public class UserDto {

    private String name;

}
