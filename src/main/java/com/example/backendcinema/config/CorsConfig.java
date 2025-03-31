package com.example.backendcinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép CORS cho tất cả các endpoint
                .allowedOrigins("http://localhost:3000") // Cho phép yêu cầu từ frontend của bạn
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Cho phép các phương thức HTTP
    }
}