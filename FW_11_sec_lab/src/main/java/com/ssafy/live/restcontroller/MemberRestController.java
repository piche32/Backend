package com.ssafy.live.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/public/members")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MemberRestController", description = "회원 가입을 위한 기능 제공")
public class MemberRestController {

    private final MemberService mService;

    @PostMapping
    @Operation(summary = "회원 가입", description = "회원 가입을 처리한다. 회원 정보는 json 문자열로 전달받는다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "500", description = "회원 가입 실패(서버오류)"),
    })
    public ResponseEntity<?> registMember(@RequestBody Member member) {
        mService.registMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("member", member));
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmailDuplicate(@RequestParam String email) {
        Member selected = mService.selectDetail(email);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("canUse", selected == null));
    }
}
