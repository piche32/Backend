package com.ssafy.live.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.service.Member;
import com.ssafy.live.model.service.SimpleService;

import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

@WebMvcTest(value = SimpleController.class) // 테스트 할 대상 Controller
@Slf4j
public class ControllerTest {

    @Autowired
    MockMvc mock; // 가상의 웹 환경을 위한 객체

    @MockitoBean // 실제 서비스를 테스트할 계획은 없다.
    SimpleService service;

    // TODO: 07-1. 매번 테스트 전에 Mock 객체 service의 동작을 정의해보자.
    //  echo("Hello")가 호출되면 "echo: Hello"를 반환한다.

    // END

    @Test
    public void simpleEchoTest() throws Exception {
        // TODO: 07-2. /simple/echo?msg=Hello 요청의 동작을 테스트 해보자.
        // given: 요청 만들기

        // END
    }

    @Test
    public void json() throws Exception {
        // TODO: 07-3. /simple/json요청의 동작을 테스트해보자.
        //  주의: content가 MockMvcResultMatchers.content() 임!
        //  MockMvcRequestBuilders.content() 아님

        // END
    }

    @Test
    public void registTest() throws Exception {

        // given
        Member member = new Member("ssafy", "1234", List.of("reading", "study"));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", member.id());
        params.add("pass", member.pass());
        params.addAll("hobby", member.hobby());

        var request = MockMvcRequestBuilders.post("/simple/regist").params(params);
        // TODO: 08-3. /simple/regist의 동작을 테스트 해보자.
        // when

        // END
    }

    @Test
    public void cookieTest() throws Exception {
        // TODO: 09-2. /simple/cookie의 동작을 테스트 해보자.
        // given

        // END
    }

    // TODO: 10-3. /simple/redir-attr에서 flash / session scope의 내용을 점검해보자.
    @Test
    public void redirectAttributeTest() throws Exception {
        // given
        var request = get("/simple/redir-attr");

        // when
        var actions = mock.perform(request);

        // then
        actions.andExpect(status().is(302))
                .andExpect(request().sessionAttribute("bySession", "annoying"))
                .andExpect(flash().attribute("byFlash", "SSG"))
                .andExpect(redirectedUrlPattern("/?byParam=dirty"));
    }
}
