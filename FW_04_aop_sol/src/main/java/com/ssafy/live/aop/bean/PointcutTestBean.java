// /src/main/java/com.ssafy.live.aop.bean.PointcutTestBean
package com.ssafy.live.aop.bean;

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.ssafy.live.aop.aspect.AuditLog;
import com.ssafy.live.model.dto.Member;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PointcutTestBean {

    @AuditLog(type = "회원가입")
    public Member save(Member member) {
        log.debug("save: {}", member);
        return member;
    }

    @AuditLog(type = "회원조회")
    public Member select(String email) {
        Member member = Member.builder().email(email).password("123456789").name("hong").build();
        log.debug("select: {}", member);
        return member;
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new RuntimeException("factorial은 0 이상의 정수 필요");
        } else if (n == 0) {
            return 0;
        }
        return IntStream.rangeClosed(1, n).reduce(1, (ov, nv) -> ov * nv);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
