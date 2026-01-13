package com.example.practicatfgbd.security;

import com.example.practicatfgbd.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;


    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    @Order(2)
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/home"),
                                AntPathRequestMatcher.antMatcher("/usuario/**"),
                                AntPathRequestMatcher.antMatcher("/webjars/**"),
                                AntPathRequestMatcher.antMatcher("/images/**"),
                                AntPathRequestMatcher.antMatcher("/css/**")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/usuario/login")
                        .permitAll())
                .logout(out -> out
                        .logoutUrl("/usuario/logout")
                        .logoutSuccessUrl("/usuario/login?home").permitAll());

        http.csrf(csrf ->
                csrf.ignoringRequestMatchers(
                        AntPathRequestMatcher.antMatcher("/"),
                        AntPathRequestMatcher.antMatcher("/webjars/**"),
                        AntPathRequestMatcher.antMatcher("/images/**"),
                        AntPathRequestMatcher.antMatcher("/css/**")));

        return http.build();
    }


}
