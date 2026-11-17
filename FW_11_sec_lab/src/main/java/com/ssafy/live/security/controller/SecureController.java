package com.ssafy.live.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.security.dto.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/secured")
@Slf4j
public class SecureController {

    @GetMapping("/admin")

    public String admin() {
        return "secured/admin/admin";
    }

    // TODO: 10-1. Controller 영역에서의 UserDetails 정보 활용을 살펴보세요.
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserDetails details) {
        if (details != null) {
            log.debug("로그인 정보: {}, {}", details.getUsername(), details.getAuthorities());
            if (details instanceof CustomUserDetails cuser) {
                //Member member = cuser.getMember();
                 //log.debug("member: {}", member.getName());
            }
        }
        return "secured/user/user";
    }
}
