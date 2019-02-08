package com.example.historical.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for generic web functions.
 * 
 * @author JX
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /* Controller End-points */
    public static final String PERMIT = "/permit";
    public static final String POC = "/poc";
    public static final String SLOT = "/slot";
    public static final String DATESLOT = "/dateslot";
    public static final String TIMESLOT = "/timeslot";
    public static final String COORDINATE = "/coordinate";
    public static final String QUERY = "/query";
    public static final String AUTHORITY = "/authority";

    /* Static Documentation End-points */
    public static final String DOCS = "/docs/**";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(DOCS).addResourceLocations("classpath:/static/");
    }

}