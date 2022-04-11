package love.korni.shopexample.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.users.AbstractRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * SecurityConfig
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SECURITY_SWAGGER_MATCHERS = {
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**"
    };
    private static final String[] SECURITY_BOOT_MATCHERS = {
            "/actuator", "/actuator/**"
    };
    private static final String[] SECURITY_LOGIN_MATCHERS = {
            "/api/auth/login", "/api/auth/registration", "/verify-email*"
    };
    private static final String[] SECURITY_FOR_ALL_MATCHERS = {
            "/*"
    };

    @Value("${auth:}")
    private String auth;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if ("off".equalsIgnoreCase(auth)) {
            log.warn("Auth is OFF");
            http.cors().and()
                    .csrf().disable()
                    .anonymous().principal("guest")
                    .and().authorizeRequests().anyRequest().anonymous();
        } else {
            // @formatter:off
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(SECURITY_SWAGGER_MATCHERS).permitAll()
                .antMatchers(SECURITY_BOOT_MATCHERS).permitAll()
                .antMatchers(SECURITY_LOGIN_MATCHERS).anonymous()
                .antMatchers(SECURITY_FOR_ALL_MATCHERS).permitAll()
                .anyRequest().authenticated()
            .and()
                .anonymous().principal("guest").authorities("READ")
            .and().httpBasic();
        // @formatter:on
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
