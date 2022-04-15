package love.korni.shopexample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * SecurityConfig
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SECURITY_SWAGGER_MATCHERS = {
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**"
    };
    private static final String[] SECURITY_BOOT_MATCHERS = {
            "/actuator", "/actuator/**"
    };
    private static final String[] SECURITY_FOR_ALL_MATCHERS = {
            "/api/v1/coffee"
    };

    @Value("${auth:}")
    private String auth;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if ("off".equalsIgnoreCase(auth)) {
            log.warn("Auth is OFF");
            http.cors().and()
                    .csrf().disable()
                    .anonymous().principal("guest").authorities("ROLE_GUEST")
                    .and().authorizeRequests().anyRequest().anonymous();
        } else {
            // @formatter:off
            http.cors().and().csrf().disable()
                .authorizeRequests()
                    .antMatchers(SECURITY_SWAGGER_MATCHERS).permitAll()
                    .antMatchers(SECURITY_BOOT_MATCHERS).permitAll()
                .and()
                    .anonymous().principal("guest").authorities("ROLE_GUEST")
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // @formatter:on
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user1 = User.builder()
                .username("user1")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password(encoder.encode("321"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(List.of(admin, user1, user2));
    }

}
