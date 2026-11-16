package com.ssafy.live.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
// TODO: 08-2. SWAGGER 설정을 확인하세요.
// 문서에 대한 설명
@OpenAPIDefinition(info = @Info(title = "SSAFY HRM API 명세서", description = "API 명세서 테스트 입니다.", version = "v1"))
public class SwaggerConfig {

    // 1. 회원 관리 관련 API
    @Bean
    GroupedOpenApi memberApi() {
        String[] paths = { "/api/v1/members/**" };
        return GroupedOpenApi.builder()
                .group("Member 관련 API")
                .pathsToMatch(paths)
                .build();
    }

    // 2. 회원 가입 등 공개 API
    @Bean
    GroupedOpenApi publicOpenApi() {
        String[] paths = { "/api/v1/public/**" };
        return GroupedOpenApi.builder()
                .group("공개 API")
                .pathsToMatch(paths)
                .build();
    }

    // 3. 기타 유틸리티 API
    @Bean
    GroupedOpenApi otherOpenApi() {
        String[] paths = { "/api/v1/etc/**" };
        return GroupedOpenApi.builder()
                .group("기타 API")
                .pathsToMatch(paths)
                .build();
    }
}
