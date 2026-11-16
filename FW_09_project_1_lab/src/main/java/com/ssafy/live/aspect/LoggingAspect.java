package com.ssafy.live.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component // 하나의 빈으로 관리
@Aspect // 하나 이상의 advice를 포함
@Slf4j

public class LoggingAspect {

    // TODO: 04-1. DAO 패키지의 모든 메서드 호출을 로깅하는 advice를 작성하세요.
	@Before("execution( * com..dao.*.*(..) )")
    public void logging(JoinPoint jp) {
        log.trace("method call: {}, {}", jp.getSignature(), Arrays.toString(jp.getArgs()));
    }
}
