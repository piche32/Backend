package com.ssafy.live;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class LombokTest {
	@Test
	void memberBuilderTest() {
		Member member = Member.builder().mno(1).email("hong").password("1234").name("홍길동").build();
		log.debug("member: {} ", member);
	}
}
