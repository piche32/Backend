package com.ssafy.live.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.resilience.annotation.EnableResilientMethods;

// TODO: 03-3. mapper를 스켄하기 위한 코드를 작성해보세요.
@Configuration
@MapperScan(basePackages = "com.ssafy.live.model.dao")
@EnableResilientMethods
public class ApplicationConfig {

}
