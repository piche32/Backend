package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Optional;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.BasicMemberService;
import com.ssafy.live.model.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            case "member-detail" -> memberDetail(request, response);
            case "member-modify-form" -> memberModifyForm(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
            case "index" -> redirect(request, response, "/index.jsp");
            case "member-modify" -> memberModify(request, response);
            case "member-delete" -> memberDelete(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    private void memberList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String word = request.getParameter("word");
        int currentPage = 1;
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr != null) {
            currentPage = Integer.parseInt(currentPageStr);
        }

        // TODO: 10. SearchCondition을 구성해서 mService의 search를 조회하세요. 
        // 결과는 /member/member-list.jsp에서 확인한다.
        Page<Member> page = mService.search(new SearchCondition(key, word, currentPage));
        request.setAttribute("page", page);
        forward(request, response, "/member/member-list.jsp");
    }

    private void memberDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 14. email parameter를 이용하여 회원 정보를 조회하고, request에 저장한다.
        //  조회 결과는 /member/member-detail.jsp에서 확인한다.

        // END
    }

    private void memberModifyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 17. 회원 정보를 조회하여 정보 수정 화면(/member/member-modify-form.jsp)으로 이동한다.

        // END
    }

    private void memberModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String role = request.getParameter("role");
        int mno = Integer.parseInt(request.getParameter("mno"));

        Member member = new Member(mno, name, email, pass, role);
        // TODO: 18. 회원 정보를 수정 처리한다. 현재 로그인된 사용자의 경우 세션에 저장된 사용자 정보도 변경해준다.

        // END
    }

    private void memberDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        // TODO: 20. memberDelete를 구현해보자.
        // 당사자가 삭제된 경우 - logout 처리 - /로 redirect
        //  다른 회원을 삭제한 경우 - 목록 보기(/auth?action=member-list&currentPage=1)로

        // END
    }
}
