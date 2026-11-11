package com.ssafy.live.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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

@WebServlet(urlPatterns = "/auth")
@SuppressWarnings("serial")
@RequiredArgsConstructor
public class AuthController extends HttpServlet implements ControllerHelper {

    private final MemberService mService;
    private final AddressService aService;
    
  
    private final String keyVworld = "83F70F59-D916-3DBE-BD0C-F43C89012750";
    private final String keySgisServiceId = "46d0d3813d9c4bc8af25"; // 서비스 id
    private final String keySgisSecurity = "bd0c408816314cdaa46f"; // 보안 key
    private final String keyData = "3kJ7va3KBs0siEVCL/KHl9hjAGdkLXYww0IqWwJb7DfbV2kSU0QKtWzXKAeendsMIQ3CpreRyY+JX1OEeFdB4A==";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = preProcessing(request, response);
        System.out.println(action);
        switch (action) {
        case "member-detail" -> memberDetail(request, response);
        case "member-list" -> memberList(request, response);
        case "member-delete" -> memberDelete(request, response);
        case "member-modify-form" -> memberModifyForm(request, response);
        case "member-modify" -> memberModify(request, response);
        case "address-delete" -> addressDelete(request, response);
        case "address-insert" -> addressInsert(request, response);
        // default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        default -> forward(request, response, "/error/404.jsp"); // 지정된 페이지로 forward
        }
    }

    private void memberDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        try {

            Member member = mService.selectDetail(email);
            request.setAttribute("member", member);
            request.setAttribute("key_vworld", keyVworld);
            request.setAttribute("key_sgis_service_id", keySgisServiceId);
            request.setAttribute("key_sgis_security", keySgisSecurity);
            request.setAttribute("key_data", keyData);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("alertMsg", e.getMessage());
        }
        forward(request, response, "/member/member-detail.jsp");
    }

    private void memberList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String word = request.getParameter("word");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        try {
            Page<Member> page = mService.search(new SearchCondition(key, word, currentPage));
            request.setAttribute("page", page);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("alertMsg", e.getMessage());
        }
        forward(request, response, "/member/member-list.jsp");
    }

    private void memberModifyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            Member member = mService.selectDetail(email);
            request.setAttribute("member", member);
            forward(request, response, "/member/member-modify-form.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // 다시 member detail 상황으로 이동
            request.setAttribute("alertMsg", e.getMessage());
            memberDetail(request, response);
        }
    }

    private void memberModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int mno = Integer.parseInt(request.getParameter("mno"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            Member member = new Member(mno, name, email, password, role);
            mService.update(member);
            // 세션 사용자의 경우 이름 변경해주기
            HttpSession session = request.getSession();
            if (((Member) session.getAttribute("loginUser")).getEmail().equals(member.getEmail())) {
                session.setAttribute("loginUser", member);
            }
            // post redirect get
            redirect(request, response, "/auth?action=member-detail&email=" + email);
        } catch (SQLException e) {
            e.printStackTrace();
            // 다시 member detail 상황으로 이동
            request.setAttribute("alertMsg", e.getMessage());
            memberDetail(request, response);
        }
    }

    private void addressInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int mno = Integer.parseInt(request.getParameter("mno"));
            String title = request.getParameter("addr_title");
            String main = request.getParameter("addr_main");
            String detail = request.getParameter("addr_detail");
            String x = request.getParameter("x");
            String y = request.getParameter("y");
            String email = request.getParameter("email");
            Address addr = new Address(0, mno, title, main, detail, x, y);
            aService.registAddress(addr);
            Member member = mService.selectDetail(email);
            toJSON(member.getAddresses(), response);
        } catch (SQLException e) {
            e.printStackTrace();
            toJSON(Map.of("error", e.getMessage()), response);
        }
    }

    private void addressDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int ano = Integer.parseInt(request.getParameter("ano"));
            String email = request.getParameter("email");
            aService.deleteAddress(ano);
            Member member = mService.selectDetail(email);
            toJSON(member.getAddresses(), response);
        } catch (SQLException e) {
            e.printStackTrace();
            toJSON(Map.of("error", e.getMessage()), response);
        }
    }

    private void memberDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int mno = Integer.parseInt(request.getParameter("mno"));
            mService.delete(mno);
            HttpSession session = request.getSession();
            if (session.getAttribute("loginUser") instanceof Member m && m.getMno() == mno) {
                session.invalidate();
                redirect(request, response, "/");
            } else {
                redirect(request, response, "/auth?action=member-list&currentPage=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("alertMsg", e.getMessage());
            memberDetail(request, response);
        }
    }
}
