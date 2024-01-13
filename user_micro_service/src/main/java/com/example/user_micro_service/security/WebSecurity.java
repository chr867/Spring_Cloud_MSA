package com.example.user_micro_service.security;

import com.example.user_micro_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity{
    private final UserService userService;
    private final Environment env;
    private final CustomAuthenticationManager authenticationManager;

    private static final String[] WHITE_LIST = {
            "/users/**",
            "/**",
            "/actuator/**"
    };

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
//        http.headers().frameOptions().disable();
        http.headers(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
            authorize.requestMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
        ).addFilter(getAuthenticationFilter())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter()
            throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager);

        return authenticationFilter;
    }


}
