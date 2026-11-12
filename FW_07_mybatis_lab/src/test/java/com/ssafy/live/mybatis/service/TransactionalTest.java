package com.ssafy.live.mybatis.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;

import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.mybatis.TxStartService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TransactionalTest {

    @Autowired
    TxStartService txService;

    @Autowired
    MemberDao mDao;

    @BeforeEach
    public void setup() {
        member = Member.builder().name("test").email("ttest@ssafy.com").password("1234").build();
        mDao.insert(member);
    }

    @AfterEach
    public void cleanup() {
        mDao.delete(member.getMno());
    }

    private Member member;

    // TODO: 17. Transaction의 전파 속성을 살펴보자.
    @Test
    @DisplayName("정상적인 경우 - 삭제가 잘 이뤄짐")
    public void propagationTest0() {
        txService.start(Propagation.REQUIRED, 1, member.getMno());
        // 다시 조회
        Member selected = mDao.select(member.getEmail());
        // 삭제 완료
        Assertions.assertNull(selected);
    }

    @Test
    @DisplayName("비정상적인 경우: REQUIRED - rollback")
    public void propagationTest1() {
        // 문제 발생 --> rollback
        Assertions.assertThrows(UnexpectedRollbackException.class,
                () -> txService.start(Propagation.REQUIRED, 0, member.getMno()));
        // 다시 조회
        Member selected = mDao.select(member.getEmail());
        // 삭제되지 않음
        Assertions.assertNotNull(selected);
    }

    @Test
    @DisplayName("비정상적인 경우: REQUIRES_NEW - rollback 없음")
    public void propagationTest2() {
        txService.start(Propagation.REQUIRES_NEW, 0, member.getMno());
        // 다시 조회
        Member selected = mDao.select(member.getEmail());
        // 삭제 완료
        Assertions.assertNull(selected);
    }

    @Test
    @DisplayName("비정상적인 경우: NESTED - rollback 없음")
    public void propagationTest3() {
        txService.start(Propagation.NESTED, 0, member.getMno());
        // 다시 조회
        Member selected = mDao.select(member.getEmail());
        // 삭제 완료
        Assertions.assertNull(selected);
    }
}
