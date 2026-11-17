package com.ssafy.live.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.live.interceptor.PerformanceInterceptor;
import com.ssafy.live.interceptor.SessionInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MvcConfigurer implements WebMvcConfigurer {
    private final PerformanceInterceptor pi;
    private final SessionInterceptor si;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(pi);
        // TODO: 06-2. SessionInterceptor의 동작을 중지하세요.
//       registry.addInterceptor(si).addPathPatterns("/auth/**");
    }

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/help").setViewName("help/auth-help");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH").allowedOrigins("*");
        registry.addMapping("/member/check-email");
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
