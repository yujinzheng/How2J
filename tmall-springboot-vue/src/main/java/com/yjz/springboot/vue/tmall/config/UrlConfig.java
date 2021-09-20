package com.yjz.springboot.vue.tmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: YU
 * @create: 2021-09-21 06:25
 **/
@Configuration
public class UrlConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/category/**").addResourceLocations("file:/E:/work/java/How2J/tmall-springboot-vue/img/category/");
    }
}
