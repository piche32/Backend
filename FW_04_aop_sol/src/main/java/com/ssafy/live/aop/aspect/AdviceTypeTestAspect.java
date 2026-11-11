package com.ssafy.live.aop.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AdviceTypeTestAspect {
    // TODO: 04-01. PointcutTestBean의 save가 호출될 때 비밀번호가 암호화되어있는지 확인하자.
    //  암호화가 되어있지 않으면(8글자 미만이면) 예외 처리
    @Before("execution(* *..PointcutTestBean.save(com.ssafy.live.model.dto.Member)) && args(member)")
    public void checkPasswordEncryption(Member member) {
        log.debug("member(aspect): {}", member);
        // 입력 값 체크
        if (member.getPassword().length() <= 8) {
            throw new IllegalArgumentException("비밀번호가 암호화되어있지 않습니다.");
        }
    }

    // TODO: 05-01. PointcutTestBean의 @AuditLog가 적용된 메서드가 호출될 때, 작업의 타입을
    // 로깅해보자.
    @AfterReturning(value = "@annotation(auditLog)", returning = "member")
    public void successWorkLog(JoinPoint jp, AuditLog auditLog, Member member) {
        log.debug("작업 성공({}), 메서드: {}", auditLog.type(), jp.getSignature());
    }

    // TODO: 06-01. P.T.Bean의 메서드에서 예외 발생 시 관리자에게 email이 전송되는 것처럼
    // 처리해보자.
    @AfterThrowing(value = "execution( * *..PointcutTestBean.*(..) )", throwing = "e")
    public void notifyError(JoinPoint jp, Exception e) {
        log.debug("signature: {}, exception: {}", jp.getSignature(), e.getMessage());
        System.out.println("시스템 관리자에게 email 전송");
    }

    // TODO: 07-01. pointcut을 읽고 적용 대상을 파악한 후 advice 동작을 확인하세요.
    @Around("execution(* *..PointcutTestBean.add(int, int))")
    public Integer modifyAdd(ProceedingJoinPoint pjp) throws Throwable { // 예외 전파

        Object[] args = pjp.getArgs();
        args[0] = (Integer) args[0] * 10; // 1. 파라미터 완전 대체 가능
        Integer result = (Integer) pjp.proceed(args); // 2. 타겟의 메서드 호출
        return result % 2 == 0 ? result : result / 2; // 3. 결과 조작 가능
    }

    private final Map<Integer, Long> cache = new HashMap<>();

    // TODO: 07-03. PointcutTestBean의 factorial이 호출될 때 값을 캐싱해보자.
    @Around(value = "execution(* *..PointcutTestBean.factorial(int)) && args(n)")
    public long caching(ProceedingJoinPoint pjp, int n) throws Throwable {
        synchronized (cache) {
            if (!cache.containsKey(n)) {
                log.debug("기존 값 없음: {}", n);
                long result = (long) pjp.proceed(pjp.getArgs());
                cache.put(n, result);
            } else {
                log.debug("기존 값 있음: {}", n);
            }
            return cache.get(n);
        }
    }
}
