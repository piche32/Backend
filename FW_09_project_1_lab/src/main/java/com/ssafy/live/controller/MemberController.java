package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 05-3. @Controller로 변경하고 service의 case에 해당하는 handler 메서드를 작성하세요.
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController{

    private final BasicMemberService mService;
    
    @GetMapping("login-form")
    public String loginForm() {
    	return "member/login-form";
    }

    @GetMapping("/regist-member-form")
    public String registMemberForm() {
    	return "member/member-regust-form";
    }
    
    @PostMapping("/regist-member")
    private String registMember(@ModelAttribute Member member, RedirectAttributes redir, Model model) {
        try {    
        	mService.registMember(member);
            String message = "등록되었습니다. 로그인 후 사용해주세요.";
            redir.addFlashAttribute("alertMsg", message);
            return "redirect:/";
        } catch (Exception e) {
        	log.error("regist member fail", e);
        	model.addAttribute("error", e.getMessage());
            return "member/member-regist-form";
        }
    }
    
    @PostMapping("/login")
    private String login(@RequestParam String email, @RequestParam(name = "password") String pass,
    		HttpSession session, Model model) {
        try {
            Member member = mService.login(email, pass);
            session.setAttribute("loginUser", member);
            
            return "redirect:/";
        } catch (Exception e) {
        	log.error("login fail", e);
        	model.addAttribute("alertMsg", e.getMessage());
            return "member/login-form";
        }
    }
    
    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/checkEmail")
    @ResponseBody //몸통에 바로 써버릴 것
    private Map<String, ?> checkEmailDuplicate(@RequestParam String email) {
        try {
            Member selected = mService.selectDetail(email);
            return Map.of("canUse", selected == null);
        } catch (Exception e) {
        	log.error("check email fail", e);
        	return Map.of("exception", e.getMessage());
        }
    }
}
