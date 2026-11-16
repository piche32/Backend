package com.ssafy.live.aspect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.dao.MemberDao;

@SpringBootTest
public class LoggingAspectTest {

    @Autowired
    private MemberDao memberDao;

    // TODO: 04-2. DAO 메서드 호출 시 AOP 로깅이 동작하는지 테스트
    @Test
    @DisplayName("DAO 메서드 호출 시 AOP 로깅이 동작하는지 테스트")
    void testLoggingAspect() {
        memberDao.selectDetail("admin@ssafy.com");
    }
}
