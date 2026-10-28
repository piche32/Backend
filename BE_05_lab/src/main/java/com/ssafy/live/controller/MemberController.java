package com.ssafy.live.controller;

import java.io.IOException;
import java.util.List;

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
        // TODO: 09-1. login 관련된 경로 처리를 확인하세요.
        case "login-form" -> forward(request, response, "/member/login-form.jsp");
        case "logout" -> logout(request, response);
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        case "member-regist" -> memberRegist(request, response);
        // TODO: 09-2. login 관련된 경로 처리를 확인하세요.
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
        // TODO: 10. 로그인 성공 시 세션에 loginUser를 키로 member를 추가하고 "/" 로 redirect 하세요.
            //  로그인 실패시는 alertMsg에 메시지를 추가하고 "/member/login-form.jsp"로 이동하세요.

        // END
    }

    private void memberListReset(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        mService.reset();
        HttpSession session = request.getSession();
        session.setAttribute("alertMsg", "회원 목록이 초기화되었습니다.");
        redirect(request, response, "/");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 11. 세션을 만료시키고 /로 redirect 하세요.

        // END
    }

    private void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
