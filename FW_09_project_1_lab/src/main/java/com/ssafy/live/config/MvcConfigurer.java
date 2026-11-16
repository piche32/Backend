package com.ssafy.live.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.live.interceptor.LoggingInterceptor;
import com.ssafy.live.interceptor.SessionInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MvcConfigurer implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    private final LoggingInterceptor li;
    private final SessionInterceptor si;
    // TODO: 03-1. 가상 경로(/upload/xx: asterisks️)를 물리 경로("file:" + filePath + "/")로 매핑하세요.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");
    	
    }
    // END

    // TODO: 06-2. 성능 측정, 세션 관리를 위한 Interceptor를 생성하고 등록해보자.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(li); //경로 생략 --> 전체 요청
    	registry.addInterceptor(si).addPathPatterns("/auth/**")
    	.excludePathPatterns("/auth/help");
    }
    // END

    // TODO: 08-1. /auth/help 요청에 대해 /help/auth-help.jsp를 연결해주자.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/auth/help").setViewName("auth/help");
	}
    // END
}
