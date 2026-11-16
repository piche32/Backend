package com.ssafy.live.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;

@SpringBootTest
// TODO: 01-4. application.properties관련 설정을 확인하세요.
class ApplicationPropertiesTest {

    @Value("${key_kakao_javascript}")
    String keyKakaoJavaScript;

    @Value("${spring.mvc.view.prefix}")
    String viewPrefix;

    @Value("${spring.mvc.view.suffix}")
    String viewSuffix;

    @Value("${spring.servlet.multipart.location}")
    String uploadLocation;

    @Value("${spring.servlet.multipart.file-size-threshold}")
    String fileSizeThreshold;

    @Value("${spring.servlet.multipart.max-file-size}")
    String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    String maxRequestSize;

    @Test
    @DisplayName("kakao 관련 키가 잘 로드되는지 테스트")
    void kakaoAPIKeyTest() {
        assertNotEquals("Your_Key_Here", keyKakaoJavaScript);
    }

    @Test
    @DisplayName("뷰 리졸버 설정이 잘 로드되는지 테스트")
    void viewResolverTest() {
        assertEquals("/WEB-INF/views/", viewPrefix);
        assertEquals(".jsp", viewSuffix);
    }

    @Test
    @DisplayName("파일 업로드 설정이 잘 로드되는지 테스트")
    void multipartTest() {
        assertEquals(System.getProperty("user.home") + "/uploads", uploadLocation);
        assertEquals("1MB", fileSizeThreshold);
        assertEquals("10MB", maxFileSize);
        assertEquals("50MB", maxRequestSize);
    }

    @Autowired
    MemberDao mDao;
    // @Autowired
    // DBUtil util;

    @Test
    void selectDetailTest() throws SQLException {
        String email = "admin@ssafy.com";
        // Member member = mDao.selectDetail(util.getConnection(), email);
        Member member = mDao.selectDetail(email);
        Assertions.assertEquals("관리자", member.getName());
    }

}
