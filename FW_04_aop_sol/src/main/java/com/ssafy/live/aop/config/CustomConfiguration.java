package com.ssafy.live.aop.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableCaching
public class CustomConfiguration {

    // @Bean // 커스텀 SimpleAsyncTaskExecutor 등록
    TaskExecutor simpleTaskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean // 커스텀 ThreadPoolTaskExecutor 등록
    TaskExecutor customTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 기본 스레드 수
        executor.setQueueCapacity(25); // 대기열 용량
        executor.setMaxPoolSize(10); // 최대 스레드 수 : 대기열을 다 채웠을 때 확장 가능한 스레드 수
        return executor;
    }
}
