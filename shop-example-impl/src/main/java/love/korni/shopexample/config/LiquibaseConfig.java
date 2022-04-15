package love.korni.shopexample.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * LiquibaseConfig
 *
 * @author Sergei_Konilov
 */
@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/database_coffee_shop_changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}
