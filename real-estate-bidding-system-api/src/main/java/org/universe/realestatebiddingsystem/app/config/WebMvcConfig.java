package org.universe.realestatebiddingsystem.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.MAP_ALL_URL;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(MAP_ALL_URL);
    }
}
