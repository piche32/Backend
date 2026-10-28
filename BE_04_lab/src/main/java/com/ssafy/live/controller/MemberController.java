package com.ssafy.live.controller;

import java.io.IOException;
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
            case "member-list-reset" -> memberListReset(request, response);
            case "member-regist-form" -> forward(request, response, "/member/member-regist-form.jsp");
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
            case "index" -> redirect(request, response, "/index.jsp");
            case "member-regist" -> memberRegist(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    private void memberRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // TODO: 04. 다음 요청 사항을 만족시키세요.
            //  01. 파라미터 추출 및 Member DTO 생성
            //  02. 비지니스 로직 전문가인 BasicMemberService의 registMember를 호출해서 회원을 등록하세요.
            //  03. 등록 성공 시 alertMsg와 함께 / 로 이동하세요. '/'에서 alertMsg를 alert으로 띄울 수 있도록 처리하세요.
            String alertMsg = "등록되었습니다. 로그인 후 사용해주세요.";

            // END
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 06. Exception 발생 시 logging 하고 error 메시지를 전달하여 다시 작성하도록 유도(/member/member-regist-form.jsp)하시오.

            // END
        }
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
