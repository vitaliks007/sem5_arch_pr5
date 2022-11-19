package ru.vitaliy.pr5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/page").setViewName("page/index.html");
    }
}
