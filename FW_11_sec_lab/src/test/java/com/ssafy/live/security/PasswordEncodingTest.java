package com.ssafy.live.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class PasswordEncodingTest {

    @Autowired
    PasswordEncoder passEncoder;

    @Test
    @DisplayName("encod된 값을 확인할 때는 matches 메서드를 사용해야 한다.")
    void passwordEncoderTest() {
        String pass = "1234";
        String encoded1 = passEncoder.encode(pass);
        String encoded2 = passEncoder.encode(pass);

        log.debug("encoded1: {}", encoded1);
        log.debug("encoded2: {}", encoded2);

        // TODO: 04-2. PasswordEncode를 이용한 비교 방식을 살펴보자.
        Assertions.assertTrue(passEncoder.matches(pass, encoded2));
        // END
    }

    @Autowired
    MemberService memberService;

    @Autowired
    MemberDao memberDao;

    @Test
    @Commit
    // TODO: 07-2. 비밀번호를 PasswordEncoder로 encoding 하세요.

    void updatePass() {
        // 1. 공통 암호를 미리 한 번만 생성
        memberDao.searchAll().forEach(m -> {
            memberDao.updatePassword(m.getEmail(), passEncoder.encode("1234"));
        });
    }

    // TODO: 07-6. 회원 가입 시 비밀번호 인코딩이 되는지 확인하세요.
    @Test
    @DisplayName("회원가입 시 비밀번호 인코딩 확인")
    void passEncodingTestWhenRegist() {
        String password = "testpass";
        Member member = Member.builder().email("test@ssafy.com").password(password).name("테스트").build();
        memberService.registMember(member);

        Member savedMember = memberDao.select(member.getEmail());

        log.debug(savedMember.getPassword());
        Assertions.assertTrue(passEncoder.matches(password, savedMember.getPassword()));
    }
}
