package com.ssafy.live.controller;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.exception.RecordNotFoundException;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class BootMemberController implements ControllerHelper {

    private final MemberService mService;

    @GetMapping("/regist-member-form")
    private String registMemberForm() {
        return "member/member-regist-form";
    }

    @GetMapping("/login-form")
    // TODO: 09-1. error 파라미터가 있다면 alertMsg를 키로 Model에 설정하세요.
     private String loginForm(Model model, @RequestParam(required = false) String error) { 
    	if(error != null) {
    		model.addAttribute("alertMsg", "email/password를 확인하세요.");
    	}
    	return "member/login-form";
    	}

    // END

    // TODO: 09-2. @PostMapping을 제거하세요.(Spring Security에서 처리)
@PostMapping("/login")
    private String login(@RequestParam String email, @RequestParam String password,
            HttpSession session, HttpServletResponse response,
            Model model) {
        try {
            Member member = mService.login(email, password);
            session.setAttribute("loginUser", member);
            return "redirect:/";
        } catch (RecordNotFoundException | DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("alertMsg", e.getMessage());
            return "member/login-form";
        }
    }

    // TODO: 08-2. GetMapping을 제거하세요.
//@GetMapping("/logout")
    private String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/regist-member")
    private String registMember(@ModelAttribute Member member, Model model, RedirectAttributes redirs) {
        try {
            mService.registMember(member);
            String message = "등록되었습니다. 로그인 후 사용해주세요.";
            redirs.addFlashAttribute("alertMsg", message);
            return "redirect:/";
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "member/member-regist-form";
        }
    }

    @GetMapping("/check-email")
    @ResponseBody
    private Map<String, ?> checkEmailDuplicate(@RequestParam String email) {
        try {
            Member selected = mService.selectDetail(email);
            return Map.of("canUse", selected == null);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }
}
