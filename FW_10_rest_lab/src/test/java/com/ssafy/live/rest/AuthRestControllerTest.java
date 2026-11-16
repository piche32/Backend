package com.ssafy.live.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.BasicAddressService;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.restcontroller.AuthRestController;

import lombok.extern.slf4j.Slf4j;

// Slice Test: WebMvc에 관련된 컴포넌트들만 로드해서 테스트 환경을 구성한다.
@WebMvcTest(controllers = { AuthRestController.class })
@Slf4j
public class AuthRestControllerTest {

        @Autowired
        MockMvc mock;

        @MockitoBean
        BasicMemberService mService;

        @MockitoBean
        BasicAddressService aService;

        // TODO: 04-2. /members/{email}에 대한 REST API를 테스트 해보자.
        @Test
        public void memberDetail() throws Exception {
                // given
                Member member = Member.builder().name("관리자").email("email").password("pass").build();
                when(mService.selectDetail("admin@ssafy.com")).thenReturn(member);

                RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/members/admin@ssafy.com");

                // when & then
                mock.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.member.name", equalTo("관리자")));

                // given
                builder = MockMvcRequestBuilders.get("/api/v1/members/notexist@ssafy.com");
                when(mService.selectDetail("notexist@ssafy.com")).thenReturn(null);

                // when & then
                mock.perform(builder).andExpect(status().isNoContent())
                                .andExpect(jsonPath("$.email", equalTo("notexist@ssafy.com")));
        }

        @Test
        public void memberListTest() throws Exception {
                Member m1 = Member.builder().name("관리자").email("admin@ssafy.com").password("1234").build();
                Member m2 = Member.builder().name("홍길동").email("hong@ssafy.com").password("1234").build();
                SearchCondition condition = new SearchCondition("email", "ssafy", 1);
                Page<Member> page = new Page<>(condition, 2, List.of(m1, m2));
                // given
                // Mockito를 이용해 mService.search(condition)의 동작을 정의한다.
                when(mService.search(condition)).thenReturn(page);

                // TODO: 05-2. 다음 요청에 대한 REST API를 테스트 해보자.
                //  02- MockMvcRequestBuilders를 이용해 요청 객체를 생성한다. GET
                //  03 - when & then을 통한 검증

                // END
        }

        // TODO: 06-2. 회원 수정을 위한 API 테스트를 검토 하세요.
        @Test
        public void memberModifyTest() throws Exception {
                // given
                Member member = Member.builder().name("관리자").email("email").password("pass").build();
                ObjectMapper mapper = new ObjectMapper();
                var builder = MockMvcRequestBuilders.put("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(member));
                // 반환 값이 void일 경우는 doNothing 사용
                doNothing().when(mService).update(member);

                // when & then
                mock.perform(builder).andExpect(status().isOk())
                                .andExpect(jsonPath("$.member.name", equalTo("관리자")));

        }

        // TODO: 07-2. 회원 삭제를 위한 API 테스트를 검토 하세요.
        @Test
        public void memberDeleteTest() throws Exception {
                // given
                RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/1");

                doNothing().when(mService).delete(1);
                // when & then
                mock.perform(builder).andExpect(status().isOk())
                                .andExpect(jsonPath("$.mno", equalTo(1)));
        }

        @Test
        public void addressAddTest() throws Exception {
                // given
                Address address = Address.builder().title("title").mno(1).address("addr").detailAddress("detail")
                                .build();
                ObjectMapper mapper = new ObjectMapper();
                RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/members/admin@ssafy.com/addresses")
                                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(address));
                Member member = Member.builder().name("관리자").email("admin@ssafy.com").password("pass")
                                .addresses(List.of(address)).build();

                // then & then
                doNothing().when(aService).registAddress(address);
                when(mService.selectDetail(member.getEmail())).thenReturn(member);

                mock.perform(builder).andExpect(status().isOk())
                                .andExpect(jsonPath("$.addresses.length()", equalTo(1)));
        }

        @Test
        public void addressDeleteTest() throws Exception {
                // given
                Address address = Address.builder().ano(1).title("title").mno(1).address("addr").detailAddress("detail")
                                .build();
                RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/members/admin@ssafy.com/addresses/1");
                Member member = Member.builder().name("관리자").email("admin@ssafy.com").password("pass")
                                .addresses(List.of(address)).build();
                // when & then
                // doNothing().when(aService).deleteAddress(1);
                when(mService.selectDetail("admin@ssafy.com")).thenReturn(member);
                mock.perform(builder).andExpect(status().isOk())
                                .andExpect(jsonPath("$.addresses.length()", equalTo(1)));
        }

        @Test
        public void profileUpdate() throws Exception {
                // 파일을 로드하기 위한 InputStream
                InputStream is = AuthRestControllerTest.class.getResourceAsStream("MemberRestControllerTest.class");
                byte[] bytes = is.readAllBytes();
                String base64 = Base64.getEncoder().encodeToString(bytes);

                // MockMultipartFile 생성
                MockMultipartFile file = new MockMultipartFile("file", "testfile.png", "image/png", bytes);

                // 요청 빌더 생성: multipart 요청은 기본적으로 post. 이를 재정의 해서 patch로 전달
                var builder = MockMvcRequestBuilders
                                .multipart("/api/v1/members/admin@ssafy.com/profile").file(file).with(request -> {
                                        request.setMethod("PATCH");
                                        return request;
                                });
                // when & then
                doNothing().when(mService).updateProfile("admin@ssafy.com", bytes);
                mock.perform(builder).andExpect(status().isOk())
                                .andExpect(jsonPath("$.img").value(base64));

                // InputStream 닫기
                is.close();
        }
}
