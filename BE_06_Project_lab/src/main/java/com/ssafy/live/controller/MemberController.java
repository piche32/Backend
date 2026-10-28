package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Map;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet implements ControllerHelper {

    private final MemberService mService = BasicMemberService.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        case "member-regist-form" -> forward(request, response, "/member/member-regist-form.jsp");
        case "member-list-reset" -> memberListReset(request, response);
        case "login-form" -> forward(request, response, "/member/login-form.jsp");
        case "logout" -> logout(request, response);
        case "email-check" -> emailCheck(request, response);
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        case "member-regist" -> memberRegist(request, response);
        case "login" -> login(request, response);
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    private void memberRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            Member member = new Member(name, email, pass);

            mService.registMember(member);

            String alertMsg = "등록되었습니다. 로그인 후 사용해주세요.";
            HttpSession session = request.getSession();
            session.setAttribute("alertMsg", alertMsg);
            redirect(request, response, "/");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            forward(request, response, "/member/member-regist-form.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Member member = mService.login(email, pass);
        if (member != null) {
            request.getSession().setAttribute("loginUser", member);
            redirect(request, response, "/");
        } else {
            request.setAttribute("alertMsg", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
            forward(request, response, "/member/login-form.jsp");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        redirect(request, response, "/");
    }

    private void emailCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 03. 전달된 email이 중복되지 않았는지 JSON으로 반환하는 메서드를 작성하세요.
        //  반환 형태: Map<String, Boolean> ex: "canUse":true

        // END
    }


    private void memberListReset(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        mService.reset();
        HttpSession session = request.getSession();
        session.setAttribute("alertMsg", "회원 목록이 초기화되었습니다.");
        redirect(request, response, "/");
    }

    private void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
