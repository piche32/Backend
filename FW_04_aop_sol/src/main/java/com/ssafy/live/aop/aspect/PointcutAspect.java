// /src/main/java/com.ssafy.live.aop.aspect.PointcutAspect
package com.ssafy.live.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PointcutAspect {
    @Pointcut("execution(* com.ssafy.service.*.*(..))")
    public void complexExecution() {
        // 내용은 필요 없음
    }

    @Before("com.ssafy.live.aop.aspect.PointcutAspect.complexExecution()")
    public void logBeforeServiceLayerExecution(JoinPoint joinPoint) {
        // 타겟 동작 전에 실행 할 일 정리
    }

    @After("com.ssafy.live.aop.aspect.PointcutAspect.complexExecution()")
    public void logAfterServiceLayerExecution(JoinPoint joinPoint) {
        // 타겟 동작 후에 실행 할 일 정리
    }

    // TODO: 03-1. pointcut이 적용되었을 때 어떤 메서드에 대한 log가 출력되는지 확인하세요.
    // @Before("execution(void
    // *..PointcutTestBean.save(com.ssafy.live.model.dto.Member))")
    // @Before("execution(!String com.ssafy..s*(*))")
    // @Before("execution(long com..*Bean.*(int, ..))")
    // @Before("execution(java.util.List<com.example.MyClass> *(..))")
    // @Before("@annotation(com.ssafy.live.aop.aspect.AuditLog)")
    // @Before("execution(* *(..))")
    public void pointcutTest(JoinPoint jp) {
        log.trace("pointcut test, 호출 메서드: {}", jp.getSignature());
    }

}
