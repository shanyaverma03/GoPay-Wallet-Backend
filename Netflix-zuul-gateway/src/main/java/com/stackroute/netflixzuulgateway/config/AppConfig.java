package com.stackroute.netflixzuulgateway.config;

import com.stackroute.netflixzuulgateway.filter.JWTValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JWTValidationFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/bank/*", "/api/v1/transaction/*", "/api/v1/auth/*", "/api/v1/user/*");
        return filterRegistrationBean;
    }

}
