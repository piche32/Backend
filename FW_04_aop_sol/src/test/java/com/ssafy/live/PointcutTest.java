package com.ssafy.live;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.aop.bean.PointcutTestBean;
import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

// /src/test/java/com.ssafy.live.AOPTest.java
@SpringBootTest
@Slf4j
public class PointcutTest {
    @Autowired
    PointcutTestBean ptBean;

    @Test
    // TODO: 03-2. pointcut이 변경될 때 적용되는 메서드를 확인하세요.
    public void pointCutTest() {
        Member member = Member.builder().name("hong").password("123456789").email("abc@def.net").build();
        ptBean.save(member);
        ptBean.select(member.getEmail());
        ptBean.factorial(0);
        ptBean.add(0, 0);
    }
}
