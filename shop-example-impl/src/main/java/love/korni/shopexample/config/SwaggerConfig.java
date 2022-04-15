package love.korni.shopexample.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author Sergei_Konilov
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Coffee Shop API")
                        .description("Coffee shop sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .components(new Components()
                        .addSecuritySchemes("basicSecurity",
                                new SecurityScheme()
                                        .scheme("basic")
                                        .type(SecurityScheme.Type.HTTP)
                                        .in(SecurityScheme.In.HEADER)
                        ));
    }

}
