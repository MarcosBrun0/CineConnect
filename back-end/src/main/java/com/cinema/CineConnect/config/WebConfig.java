package com.cinema.CineConnect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Pega o valor "file.upload-dir" do application.properties
    // Isso garante que usamos a mesma pasta que o MovieService usa
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // O Mapeamento Mágico:
        // 1. Quando a URL for: http://localhost:8080/uploads/batman.jpg
        // 2. O Spring vai ler: ./uploads/batman.jpg no disco

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}