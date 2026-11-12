package com.ssafy.live.mybatis.service.resilience;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.service.ResilienceService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ResilienceServiceTest {
    @Autowired
    ResilienceService rService;

    // TODO: 18-2. Resilience의 Concurrency 기능을 테스트 해보자.
    @Test
    void concurrencyTest() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 100).forEach(i -> executor.submit(() -> {
                try {
                    log.debug("thread: {}, count: {}", Thread.currentThread(), rService.concurrency(i));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }));
        }
    }

    // TODO: 18-4. Resilience의 Retry 기능을 테스트 해보자.
    @Test
    void retryTest() {
        for (int i = 0; i < 2; i++) {
            try {
                int result = rService.transfer();
                log.debug("결과 : {}", result);
            } catch (Exception e) {
                log.error("예외 발생 : {}", e.getMessage());
            }
        }
    }
}
