package com.freedom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    // 上传目录
    @Value("${freedom.default.storage.upload:/tmp}")
    private String uploadPath;

    // 资源目录
    @Value("${freedom.default.storage.assets:/tmp}")
    private String assetsPath;

    // Cors 跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                // .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PATCH", "HEAD", "OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // classpath: file:
        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadPath + "/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:" + assetsPath + "/");
    }

    // 设置默认 ViewController
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/").setViewName("index");
    }
}
