package com.example.backendcinema.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dcoviwlpx");
        config.put("api_key", "277818239692376");
        config.put("api_secret", "XSNE4gIJQ_ESzUVLO52dVN4-qjE");

        return new Cloudinary(config);
    }
}
