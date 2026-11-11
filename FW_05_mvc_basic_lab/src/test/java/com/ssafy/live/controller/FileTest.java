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
public class FileTest {

    @Autowired
    MockMvc mock; // 가상의 웹 환경을 위한 객체

    @MockitoBean // 실제 서비스를 테스트할 계획은 없다.
    SimpleService service;

    @Test
    public void uploadTest() throws Exception {
        // given
        InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
        MockMultipartFile file = new MockMultipartFile("file", "application.properties", null, is);
        when(service.uploadFile(any(MultipartFile.class), eq("설명")))
                .thenReturn(Map.of("fileName", "application.properties", "desc", "설명",
                        "base64", "data:text/plain;base64,ZmFrZQ=="));
        var request = MockMvcRequestBuilders
                .multipart("/simple/fileupload")
                .file(file)
                .param("desc", "설명");
        // when
        ResultActions actions = mock.perform(request);
        // then
        actions.andExpect(status().is(200))
                .andExpect(model().attribute("desc", "설명"))
                .andExpect(model().attribute("base64", CoreMatchers.startsWith("data:")));
    }
}
