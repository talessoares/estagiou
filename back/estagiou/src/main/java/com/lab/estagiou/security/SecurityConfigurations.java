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

    private static final String API_VERSION = "/v1";

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
        return authorize.anyRequest().permitAll();
    }
    
    private void authRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/auth/login").permitAll();
        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/auth/logout").authenticated();
    }
    
    private void studentRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        final String STUDENT_BY_ID = "/student/*/";

        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/student/register").permitAll();
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + "/student/list").permitAll();
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + STUDENT_BY_ID).hasRole(USER);
        authorize.requestMatchers(HttpMethod.DELETE, API_VERSION + STUDENT_BY_ID).hasRole(USER);
        authorize.requestMatchers(HttpMethod.PUT, API_VERSION + STUDENT_BY_ID).hasRole(USER);
    }

    private void companyRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        final String COMPANY_BY_ID = "/company/*/";

        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/company/register").hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + "/company/list").authenticated();
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + COMPANY_BY_ID).authenticated();
        authorize.requestMatchers(HttpMethod.DELETE, API_VERSION + COMPANY_BY_ID).hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.PUT, API_VERSION + COMPANY_BY_ID).hasRole(COMPANY);
    }

    private void jobVacancyRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        final String JOB_VACANCY_BY_ID = "/jobvacancy/*/";

        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/jobvacancy/register").hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + "/jobvacancy/list").hasRole(USER);
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + JOB_VACANCY_BY_ID).hasRole(USER);
        authorize.requestMatchers(HttpMethod.DELETE, API_VERSION +  JOB_VACANCY_BY_ID).hasRole(COMPANY);
        authorize.requestMatchers(HttpMethod.PUT, API_VERSION + JOB_VACANCY_BY_ID).hasRole(COMPANY);
    }

    private void adminRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        final String ADMIN_BY_ID = "/admin/*/";
        
        authorize.requestMatchers(HttpMethod.POST, API_VERSION + "/admin/register").hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + "/admin/list").hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.GET, API_VERSION + ADMIN_BY_ID).hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.DELETE, API_VERSION + ADMIN_BY_ID).hasRole(ADMIN);
        authorize.requestMatchers(HttpMethod.PUT, API_VERSION + ADMIN_BY_ID).hasRole(ADMIN);
    }
    
}
