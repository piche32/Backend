package com.ssafy.live.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// TODO: 12-4. WebMvcConfigurer를 구현하고 가상 경로(/upload/**)를 물리 경로("file:" +
// filePath + "/")로 매핑하세요.
 public class MvcConfigurer implements WebMvcConfigurer {
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");
	}
}

// END
