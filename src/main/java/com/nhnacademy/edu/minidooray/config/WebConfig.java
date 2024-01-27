package com.nhnacademy.edu.minidooray.config;

import com.nhnacademy.edu.minidooray.interceptor.LoginInterceptor;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/signup");

//        registry.addInterceptor(new ProjectMemberCheckInterceptor(applicationContext))
//                .excludePathPatterns("/projects/list", "/projects/{projectId}",
//                        "/logout", "/withdraw");
//
//        registry.addInterceptor(new ProjectMemberCheckInterceptorBefore(applicationContext))
//                .addPathPatterns("/projects/{projectId}")
//                .excludePathPatterns("/projects/list");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(5L))
                .setReadTimeout(Duration.ofSeconds(5L))
                .build();
    }
}
