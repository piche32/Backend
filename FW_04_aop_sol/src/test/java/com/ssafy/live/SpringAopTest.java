package com.ssafy.live;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.core.task.TaskExecutor;

import com.ssafy.live.aop.bean.HeavyWorkBean;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SpringAopTest {

    @Autowired
    HeavyWorkBean hbean;

    @Autowired
    TaskExecutor executor;

    @Test
    // TODO: 08. heavyWork를 동작시키면서 빈의 타입과 Thread 기반 동작을 확인해보자.
    public void asyncTest() throws InterruptedException {
        Assertions.assertTrue(AopUtils.isAopProxy(hbean));
        log.debug("executor: {}", executor.getClass().getName());
        for (int i = 0; i < 10; i++) {
            hbean.heavyWork(i);
        }
        // SimpleAsyncTaskExecutor 사용시는 필요
        // Thread.sleep(1000 * 15); // 메인 스레드 종료 대기
    }

    @Autowired
    CacheManager cm;

    @Test
    // TODO: 09. cacheableGugu를 두 번 호출하여 캐시 동작을 확인해보자.
    public void cacheableTest() {
        List<String> first = hbean.cacheableGugu(5);
        List<String> second = hbean.cacheableGugu(5);
        Assertions.assertEquals(first, second);
        // 로그를 통해 캐시 미적용 메시지가 한 번만 출력되는지 확인
        log.debug("Cache names: {}", cm.getClass().getName());
    }

    // TODO: 10. InnerCallBean의 methodA와 methodB를 호출하여 AOP가 적용되는지 확인해보자.
    @Test
    public void innerCallTest1() {
        hbean.methodA();
    }

    @Test
    public void innerCallTest2() {
        hbean.methodB();
    }
}
