package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ssafy.live.aop.bean.PointcutTestBean;
import com.ssafy.live.model.dto.Member;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class AdviceTypeTest {
    @Autowired
    PointcutTestBean ptBean;

    @Test
    // TODO: 04-02. advice가 적용된 결과 오류가 발생하는 것을 확인하세요.
    public void beforetest() {
        Member member = Member.builder().email("abc@def.net").password("1234").name("hong").build();
        IllegalArgumentException e =Assertions.assertThrows(IllegalArgumentException.class  , ()->ptBean.save(member));
        
        Assertions.assertEquals("비밀번호가 암호화되어있지 않습니다.",e.getMessage());
    }

    @Test
    // TODO: 05-02. 작업 정상 완료 시 결과 로그가 출력되는지 확인하세요.
    public void afterReturningTest() {
        Member member = ptBean.select("admin@ssafy.com");
        ptBean.save(member);
    }

    @Test
    // TODO: 06-02.advice가 적용된 결과 예외 발생 시 email 전송을 확인하세요.
    public void afterThrowingTest() {
        Assertions.assertThrows(RuntimeException.class, () -> ptBean.factorial(-1));
    }

    @Test
    // TODO: 07-02.advice가 적용된 결과를 확인하세요.
    public void aroundTest1() {
        long result = ptBean.add(5, 6);
        Assertions.assertEquals(56, result);

        result = ptBean.add(5, 7);
        Assertions.assertEquals(28, result);
    }

    // TODO: 07-04. factorial을 호출하고 cache가 적절히 동작하는지 확인하세요.
    @Test
    public void aroundTest2() {
        long result = ptBean.factorial(5);
        result = ptBean.factorial(5);
        Assertions.assertEquals(120, result);
    }

}
 // 예외 없이 통과해야 함
