package com.ssafy.live.model.service;

import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.resilience.annotation.ConcurrencyLimit.ThrottlePolicy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResilienceService {
    private int retryCount = 0;

    // TODO: 18-1. 동시 호출 제어를 위한 annotation을 선언해보자.
    // @ConcurrencyLimit(limit = 10, policy = ThrottlePolicy.REJECT) // case 3
    public int concurrency(int id) throws InterruptedException {
        log.debug("메서드 동작함 {}", id);
        Thread.sleep(1000 * 10);
        return id;
    }

    // TODO: 18-3. 재시도를 위한 annotation을 선언해보자.
    public int transfer() {
        this.retryCount++;

        log.debug("이체 시도(재시도회수: {})", this.retryCount);

        if (this.retryCount % 3 != 0) {
            throw new RuntimeException("문제 발생: " + this.retryCount);
        } else {
            this.retryCount = 0;
            log.debug("이체 성공");
        }
        return this.retryCount;
    }
}
