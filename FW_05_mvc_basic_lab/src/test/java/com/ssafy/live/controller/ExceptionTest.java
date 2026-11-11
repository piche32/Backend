package com.ssafy.live.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ssafy.live.configure.MvcExceptionHandler;
import com.ssafy.live.model.service.SimpleService;

@WebMvcTest(controllers = SimpleController.class)
@Import(MvcExceptionHandler.class)
public class ExceptionTest {
    // TODO: 11-2. 예외 상황을 잘 처리하고 있는지 테스트 해보세요.
    @Autowired
    private MockMvc mock;

    @MockitoBean // 직접 사용하지 않아도 Controller 구성을 위해서 필요
    private SimpleService simpleService;

    @Test
    @DisplayName("404 Not Found: 존재하지 않는 URL 요청 시 error/404 뷰로 포워딩")
    public void test404() throws Exception {
        mock.perform(get("/member/login"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/404.jsp"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    @DisplayName("405 Method Not Allowed: POST 전용 매핑에 GET 요청 시 error/405 뷰로 포워딩")
    public void test405() throws Exception {
        mock.perform(get("/simple/regist"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())
                .andExpect(view().name("error/405"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/405.jsp"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    @DisplayName("500 Internal Server Error: 컨트롤러 내부 0 나누기 예외 발생 시 error/500 뷰로 포워딩")
    public void test500Problem() throws Exception {
        mock.perform(get("/simple/problem"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(view().name("error/500"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/500.jsp"))
                .andExpect(model().attributeExists("exception"));
    }
}
