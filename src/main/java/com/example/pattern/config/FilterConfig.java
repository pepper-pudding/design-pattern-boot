package com.example.pattern.config;

import com.example.pattern.config.filter.MemberCheckFilter;
import com.example.pattern.config.filter.UserAgentCheckFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfig {
    // 책임 연쇄 패턴
    // 검증 절차가 자유롭게 추가될 수 있어야 하고, 순서도 자유롭게 변경할 수 있어야 합니다 => Filter 사용!
    @Bean
    @Order(0) // 이게 먼저 수행된다.
    public FilterRegistrationBean userAgentCheckFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean((Filter)new UserAgentCheckFilter());
        registrationBean.addUrlPatterns("/orders/*");
        return registrationBean;
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean memberCheckFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean((Filter)new MemberCheckFilter());
        registrationBean.addUrlPatterns("/orders/*");
        return registrationBean;
    }
}
