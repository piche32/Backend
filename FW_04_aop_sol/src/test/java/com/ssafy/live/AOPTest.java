package com.ssafy.live;

import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ssafy.live.aop.bean.PointcutTestBean;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

// /src/test/java/com.ssafy.live.AOPTest.java
@SpringBootTest
@Slf4j
public class AOPTest {
    // TODO: 01-2. MemberService와 DAO를 주입받고 selectDetail 메서드의 동작을 테스트해보자.
    @Autowired
    MemberService mService;
    @Autowired
    MemberDao mDao;

    @Test
    public void selectDetailTest() throws SQLException {
        // given
        String email = "admin@ssafy.com";
        String name = "관리자";
        // when
        Member member = mService.selectDetail(email);
        // then
        Assertions.assertEquals(name, member.getName());
        Assertions.assertTrue(AopUtils.isAopProxy(mDao));
        Assertions.assertTrue(AopUtils.isCglibProxy(mDao));
        log.debug("mDao: {}", mDao.getClass().getName()); // com.ssafy.live.model.dao.MemberDao$$EnhancerBySpringCGLIB$$0
    }
}
