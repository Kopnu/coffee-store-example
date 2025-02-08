package love.korni.shopexample.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author Sergei_Konilov
 */
@Configuration

@io.swagger.v3.oas.annotations.security.SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicSecurity",
        scheme = "basic",
        in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Coffee Shop API")
                        .description("Coffee shop sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")));
    }

}
