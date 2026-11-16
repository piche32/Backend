package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.AddressService;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 05-4. @Controller로 변경하고 service의 case에 해당하는 handler 메서드를 작성하세요.
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController{
    private final MemberService mService;
    private final AddressService aService;

    // TODO: 05-5. 다음의 키를 application.properties에 등록하고 @Value로 참조하세요.
    private String keyKakaoJavaScript = "자바 스크립트 키";
    
    @GetMapping("/member-List")
    private String memberList(@ModelAttribute SearchCondition condition, Model model) {
        try {
            Page<Member> page = mService.search(condition);
            model.addAttribute("page", page);
        } catch (Exception e) {
        	log.error("member list fail", e);
        	model.addAttribute("alertMsg", e.getMessage());
        }
        return "member/member-list";
    }
//    
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = getActionParameter(request, response);
//        System.out.println(action);
//        switch (action) {
//            case "member-detail" -> memberDetail(request, response);
//            case "member-list" -> memberList(request, response);
//            case "member-modify-form" -> memberModifyForm(request, response);
//            case "member-modify" -> memberModify(request, response);
//            case "member-delete" -> memberDelete(request, response);
//            case "address-insert" -> addressInsert(request, response);
//            case "address-delete" -> addressDelete(request, response);
//            default -> forward(request, response, "/error/404.jsp"); // 지정된 페이지로 forward
//        }
//    }
//
//    private void memberDetail(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String email = request.getParameter("email");
//        try {
//
//            Member member = mService.selectDetail(email);
//            request.setAttribute("member", member);
//            request.setAttribute("keyKakaoJavaScript", keyKakaoJavaScript);
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("alertMsg", e.getMessage());
//        }
//        forward(request, response, "/member/member-detail.jsp");
//    }
//
//    
//
//    private void memberModifyForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String email = request.getParameter("email");
//        try {
//            Member member = mService.selectDetail(email);
//            request.setAttribute("member", member);
//            forward(request, response, "/member/member-modify-form.jsp");
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 다시 member detail 상황으로 이동
//            HttpSession session = request.getSession();
//            session.setAttribute("alertMsg", e.getMessage());
//            redirect(request, response, "/auth?action=member-detail&email=" + email);
//        }
//    }
//
//    private void memberModify(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String role = request.getParameter("role");
//        int mno = Integer.parseInt(request.getParameter("mno"));
//
//        try {
//            Member member = new Member(mno, name, email, password, role);
//            mService.update(member);
//            // 세션 사용자의 경우 이름 변경해주기
//            HttpSession session = request.getSession();
//            if (((Member) session.getAttribute("loginUser")).getEmail().equals(member.getEmail())) {
//                session.setAttribute("loginUser", member);
//            }
//            // post redirect get
//            redirect(request, response, "/auth?action=member-detail&email=" + email);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 다시 member detail 상황으로 이동
//            HttpSession session = request.getSession();
//            session.setAttribute("alertMsg", e.getMessage());
//            redirect(request, response, "/auth?action=member-detail&email=" + email);
//        }
//    }
//
//    private void memberDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int mno = Integer.parseInt(request.getParameter("mno"));
//        String email = request.getParameter("email");
//        try {
//            mService.delete(mno);
//            HttpSession session = request.getSession();
//            if (session.getAttribute("loginUser") instanceof Member m && m.getMno() == mno) {
//                session.invalidate();
//                redirect(request, response, "/");
//            } else {
//                session.setAttribute("alertMsg", "삭제되었습니다.");
//                redirect(request, response, "/auth?action=member-list&currentPage=1");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            HttpSession session = request.getSession();
//            session.setAttribute("alertMsg", e.getMessage());
//            redirect(request, response, "/auth?action=member-detail&email=" + email);
//        }
//    }
//
//    private void addressInsert(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int mno = Integer.parseInt(request.getParameter("mno"));
//            String title = request.getParameter("addr_title");
//            String main = request.getParameter("addr_main");
//            String detail = request.getParameter("addr_detail");
//            String x = request.getParameter("x");
//            String y = request.getParameter("y");
//            String email = request.getParameter("email");
//            Address addr = Address.builder()
//                    .ano(0)
//                    .mno(mno)
//                    .title(title)
//                    .address(main)
//                    .detailAddress(detail)
//                    .x(x)
//                    .y(y)
//                    .build();
//            aService.registAddress(addr);
//            Member member = mService.selectDetail(email);
//            toJSON(member.getAddresses(), response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            toJSON(Map.of("error", e.getMessage()), response);
//        }
//    }
//
//    private void addressDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            int ano = Integer.parseInt(request.getParameter("ano"));
//            String email = request.getParameter("email");
//            aService.deleteAddress(ano);
//            Member member = mService.selectDetail(email);
//            toJSON(member.getAddresses(), response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            toJSON(Map.of("error", e.getMessage()), response);
//        }
//    }
}
