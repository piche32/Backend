package com.ssafy.live.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
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

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(pi);
        registry.addInterceptor(si).addPathPatterns("/auth/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/help").setViewName("help/auth-help");
    }

    // TODO: 01-1. api versioning 관련 설정을 url path 형태(/api/1/..)로 추가해보자.
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer
                // 1. /api/ 로 시작하는 요청에 대해서만 1번 세그먼트를 버전으로 추출! /api/{version}/some
                .usePathSegment(1, path -> path.value().startsWith("/api/"))
                // 2. 지원하는 API 버전 명시
                .addSupportedVersions("v0", "v1")
                // 3. 기본 버전 명시
                .setDefaultVersion("v1");
    }

    // TODO: 09-1. /api/v1/ 하위의 모든 요청에 대해 CORS를 설정해주자.

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("api/v1/**")
    	.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") //안 쓰면 전체
    	.allowedOrigins("*");
    }
    
    // END

}
