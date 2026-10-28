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

@WebServlet(urlPatterns = "/auth")
@SuppressWarnings("serial")
public class AuthController extends HttpServlet implements ControllerHelper {

    private final MemberService mService = BasicMemberService.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        case "member-list" -> memberList(request, response);
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    private void memberList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 19. 전체 Member의 정보를 반환해보자.
        //  /member/member-list.jsp에서 확인하세요.

        // END
        forward(request, response, "/member/member-list.jsp");
    }
}
