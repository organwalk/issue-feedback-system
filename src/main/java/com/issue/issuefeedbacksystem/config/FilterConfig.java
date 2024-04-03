package com.issue.issuefeedbacksystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issue.issuefeedbacksystem.filter.JwtAuthenticationFilter;
import com.issue.issuefeedbacksystem.filter.RoleState;
import com.issue.issuefeedbacksystem.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final RoleState roleState;
    private final AllowApiConfig allowApi;
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthenticationFilter(jwtUtil, objectMapper, roleState, allowApi));
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
