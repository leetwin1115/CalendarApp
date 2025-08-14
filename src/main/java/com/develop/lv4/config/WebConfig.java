package com.develop.lv4.config;

import com.develop.lv4.filter.AuthFilter;
import com.develop.lv4.repository.UserRepository;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final UserRepository userRepository;

    @Bean
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(userRepository));
        registrationBean.addUrlPatterns("/api/calendars/*");
        return registrationBean;
    }
}