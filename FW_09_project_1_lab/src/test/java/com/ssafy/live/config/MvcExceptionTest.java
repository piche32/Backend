package com.ssafy.live.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

// TODO: 02-2. MvcExceptionHandler와 CustomErrorAttributes 동작 확인 테스트

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MVC 예외 처리 통합 테스트")
public class MvcExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 404 테스트: 존재하지 않는 경로로 요청
     *
     * 동작 확인:
     * 1. CustomErrorAttributes가 로그를 남김
     * 2. BasicErrorController에서 JSON 응답 반환
     * 3. 상태 코드 404 확인
     */
    @Test
    @DisplayName("404 - 존재하지 않는 경로로 요청할 때 CustomErrorAttributes 동작 확인")
    void testNotFound() throws Exception {
        mockMvc.perform(get("/some"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * 405 테스트: 허용되지 않은 HTTP 메서드로 요청
     * 예: GET 메서드만 허용하는 엔드포인트에 POST 요청
     *
     * 동작 확인:
     * 1. CustomErrorAttributes가 로그를 남김
     * 2. BasicErrorController에서 JSON 응답 반환
     * 3. 상태 코드 405 확인
     */
    @Test
    @DisplayName("405 - 허용되지 않은 HTTP 메서드로 요청할 때 CustomErrorAttributes 동작 확인")
    void testMethodNotAllowed() throws Exception {
        // "/" 는 GET만 허용되므로 POST 요청 시 405 반환
        mockMvc.perform(post("/"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    /**
     * @Controller에서 발생한 예외가 MvcExceptionHandler에서 처리되는지 확인
     *
     *               테스트 조건:
     *               - 1/0 코드 실행으로 ArithmeticException 발생
     *               - MvcExceptionHandler가 처리하여 커스텀 에러 페이지 반환
     */
    @Test
    @DisplayName("MvcExceptionHandler - @Controller에서 발생한 예외 처리 확인")
    void testControllerExceptionHandling() throws Exception {
        // 존재하지 않는 멤버로 체크
        mockMvc.perform(get("/problem"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
