package com.ssafy.live.aop.bean;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class HeavyWorkBean {

    @Async
    public void heavyWork(int i) throws InterruptedException {
        Thread.sleep(1000);
        log.trace("{}에 의해 처리중", Thread.currentThread().getName());
    }

    /**
     * @Cacheable 예시: 동일한 입력값에 대해 캐시된 결과를 반환
     */
    @Cacheable("guguCache")
    public List<String> cacheableGugu(int n) {
        log.info("캐시 미적용: 실제 계산 수행 - n={}", n);
        List<String> result = new java.util.ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            result.add(n + " x " + i + " = " + (n * i));
        }
        return result;
    }

    public void methodA() {
        log.debug("methodA called");
    }

    public void methodB() {
        log.debug("methodB called");
        methodA();
    }
}
