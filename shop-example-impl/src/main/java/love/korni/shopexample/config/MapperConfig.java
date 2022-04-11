package love.korni.shopexample.config;

import love.korni.shopexample.domain.entity.Coffee;
import love.korni.shopexample.dto.CoffeeDto;

import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

/**
 * MapperConfig
 *
 * @author Sergei_Konilov
 */
@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(@NotNull MapperFactory mapperFactory) {
        mapperFactory.classMap(Coffee.class, CoffeeDto.class)
                .byDefault()
                .register();
    }
}
