package com.lab.estagiou.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
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
            .authorizeHttpRequests(this::configureRequests)
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

    private AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configureRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authRequests(authorize);
        studentRequests(authorize);
        companyRequests(authorize);
        jobVacancyRequests(authorize);
        adminRequests(authorize);
        return authorize;
    }
    
    private void authRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST,"/v1/auth/login").permitAll();
        authorize.requestMatchers(HttpMethod.POST,"/v1/auth/register").permitAll();
    }
    
    private void studentRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST,"/v1/student/register").permitAll();
        authorize.requestMatchers(HttpMethod.GET, "/v1/student/list").permitAll();
        authorize.requestMatchers(HttpMethod.GET, "/v1/student/*/").hasRole(USER);
        authorize.requestMatchers(HttpMethod.DELETE, "/v1/student/*/").hasRole(USER);
        authorize.requestMatchers(HttpMethod.PUT, "/v1/student/*/").hasRole(USER);
    }

    private void companyRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST,"/v1/company/register").hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.GET,"/v1/company/list").authenticated();
        authorize.requestMatchers(HttpMethod.GET,"/v1/company/*/").authenticated();
        authorize.requestMatchers(HttpMethod.DELETE,"/v1/company/*/").hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.PUT,"/v1/company/*/").hasRole(COMPANY);
    }

    private void jobVacancyRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST,"/v1/jobvacancy/register").hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.GET,"/v1/jobvacancy/list").hasRole(USER);
        authorize.requestMatchers(HttpMethod.GET,"/v1/jobvacancy/*/").hasRole(USER);
        authorize.requestMatchers(HttpMethod.DELETE,"/v1/jobvacancy/*/").hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.PUT,"/v1/jobvacancy/*/").hasRole(COMPANY);
    }

    private void adminRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST,"/v1/admin/register").hasRole(ADMIN);
    }
    
}
