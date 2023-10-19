package com.project.fmproject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuração CORS para "/empresas"
        registry.addMapping("/empresas/**")
                .allowedOrigins("http://localhost:4200", "http://ec2-13-48-104-78.eu-north-1.compute.amazonaws.com:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);

        // Configuração CORS para "/equipamentos"
        registry.addMapping("/equipamentos/**")
                .allowedOrigins("http://localhost:4200", "http://ec2-13-48-104-78.eu-north-1.compute.amazonaws.com:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);

        // Configuração CORS para "/usuarios"
        registry.addMapping("/usuarios/**")
                .allowedOrigins("http://localhost:4200", "http://ec2-13-48-104-78.eu-north-1.compute.amazonaws.com:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}
