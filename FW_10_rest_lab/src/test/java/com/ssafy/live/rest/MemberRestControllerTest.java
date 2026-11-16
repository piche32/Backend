package com.ssafy.live.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.restcontroller.MemberRestController;

import lombok.extern.slf4j.Slf4j;

// Slice Test: WebMvc에 관련된 컴포넌트들만 로드해서 테스트 환경을 구성한다.
@WebMvcTest(controllers = { MemberRestController.class })
@Slf4j
public class MemberRestControllerTest {

    @Autowired
    MockMvc mock;

    @MockitoBean
    BasicMemberService mService;

    @Test
    // TODO: 03-2. rest api를 테스트하는 코드를 살펴보자.(@RequestBody로 설정한 후 테스트 할 것)
    public void registMemberTest() throws Exception {
        // given
        Member member = Member.builder().name("name").email("mvctest@ssafy.com").password("pass").build();
        ObjectMapper mapper = new ObjectMapper();
        RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/public/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(member));

        // MockitoBean으로 등록한 mService의 동작을 정의
        when(mService.registMember(member)).thenReturn(1);
        // when & then
        mock.perform(builder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.member.email", equalTo(member.getEmail())));
    }
}
