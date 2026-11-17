package com.ssafy.live.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ssafy.live.security.config.CustomSecurityConfig;
import com.ssafy.live.security.controller.SecureController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(value = SecureController.class)
@Import(CustomSecurityConfig.class) // 필요한 config만 로딩
public class UserDetailsServiceTest {

    @Autowired
    PasswordEncoder passEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MockMvc mockMvc;

    // TODO: 05-2. admin으로 등록된 사용자 정보를 확인하세요.
    @Test
    @DisplayName("UserDetailsService의 loadUserByUsername으로 사용자 정보를 로딩한다.")
    void 사용자생성_테스트() {
        UserDetails user = userDetailsService.loadUserByUsername("admin@ssafy.com");

        Assertions.assertNotNull(user);
        Assertions.assertTrue(passEncoder.matches("1234", user.getPassword()));
        Assertions.assertTrue(user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    @DisplayName("인증 없는 상태에서 여러 엔드포인트 접근 테스트")
    void notAuthenticatedTest() throws Exception {
        // given
        var req = MockMvcRequestBuilders.get("/");
        // when & then
        mockMvc.perform(req).andExpect(MockMvcResultMatchers.status().isOk());

        // TODO: 06-3. 인증 상태와 관련된 페이지의 이동에 대해서 확인하세요.
        // AuthenticationEntryPoint에 의해 인증이 필요한 페이지는 /login 페이지로 리다이렉트 되어야 한다.
        req = MockMvcRequestBuilders.get("/secured/admin");
        mockMvc.perform(req).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                // login customize 전
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
        // login customize 후
        // .andExpect(MockMvcResultMatchers.redirectedUrl("/member/login-form")); //
        ;
    }

    // TODO: 06-4. 권한에 따른 접근 허용에 대해서 확인하세요.
    @Test
    @DisplayName("USER 권한이 있는 사용자가 권한별 페이지에 접근하는 경우를 테스트")
    @WithMockUser(roles = "USER") // 테스트용 권한의 사용자
    void userRoleTest() throws Exception {
        mockMvc.perform(get("/secured/user")).andExpect(status().isOk());
        mockMvc.perform(get("/secured/admin")).andExpect(status().isForbidden());
    }
}
