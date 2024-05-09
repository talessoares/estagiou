package com.lab.estagiou.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lab.estagiou.model.user.UserRoleEnum;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private static final String ADMIN = UserRoleEnum.ADMIN.getRole();

    private static final String USER = UserRoleEnum.USER.getRole();

    private static final String COMPANY = UserRoleEnum.COMPANY.getRole();

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityFilter securityFilter) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize

                /* AUTH */
                .requestMatchers(HttpMethod.POST,"/v1/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/v1/auth/logout").permitAll()

                /* STUDENT */
                .requestMatchers(HttpMethod.POST,"/v1/student/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/student/list").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/student/*/").hasRole(USER)
                .requestMatchers(HttpMethod.DELETE, "/v1/student/*/").hasRole(USER)
                .requestMatchers(HttpMethod.PUT, "/v1/student/*/").hasRole(USER)

                /* COMPANY */
                .requestMatchers(HttpMethod.POST,"/v1/company/register").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/company/list").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/company/*/").hasRole(USER)
                .requestMatchers(HttpMethod.DELETE,"/v1/company/*/").hasRole(COMPANY)
                .requestMatchers(HttpMethod.PUT,"/v1/company/*/").hasRole(COMPANY)

                /* JOB VACANCY */
                .requestMatchers(HttpMethod.POST,"/v1/jobvacancy/register").hasRole(COMPANY)
                .requestMatchers(HttpMethod.GET,"/v1/jobvacancy/list").hasRole(USER)
                .requestMatchers(HttpMethod.GET,"/v1/jobvacancy/*/").hasRole(USER)
                .requestMatchers(HttpMethod.DELETE,"/v1/jobvacancy/*/").hasRole(COMPANY)
                .requestMatchers(HttpMethod.PUT,"/v1/jobvacancy/*/").hasRole(COMPANY)

                /* ADMIN */
                .requestMatchers(HttpMethod.POST,"/v1/admin/register").hasRole(ADMIN)

                .anyRequest().permitAll()
            )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
