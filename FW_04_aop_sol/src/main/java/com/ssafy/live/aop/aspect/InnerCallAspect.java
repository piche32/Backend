package com.ssafy.live.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class InnerCallAspect {

    @Before("execution(void *..HeavyWorkBean.method*())")
    void prepareMethodA(JoinPoint jp) {
        log.debug("method 호출 준비: {}", jp.getSignature());
    }
}
