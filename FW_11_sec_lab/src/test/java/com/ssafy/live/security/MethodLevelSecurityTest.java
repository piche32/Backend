package com.ssafy.live.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.security.config.CustomSecurityConfig;
import com.ssafy.live.security.controller.MethodLevelSecureController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = MethodLevelSecureController.class)
@Import(CustomSecurityConfig.class)
public class MethodLevelSecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("@Secured 로 보호되는 리소스 호출 확인")
    void secureAnnotatedMethodCallTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/method-level-secure/user")
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/method-level-secure/admin")
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("user").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}
