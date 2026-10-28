package com.ssafy.live.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.live.model.service.BasicSimpleService;
import com.ssafy.live.model.service.SimpleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet implements ControllerHelper {

    private final SimpleService sService = BasicSimpleService.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        case "gugu-form" -> forward(request, response, "/gugu/gugu-form.jsp");
        case "gugu" -> gugu(request, response);
        // TODO: 02. make-cookie와 check-cookie 요청을 처리를 확인하세요. 
        case "make-cookie" -> makeCookie(request, response);
        case "check-cookie" -> forward(request, response, "/status/check-cookie.jsp");



        // TODO: 13. exception 요청을 확인하세요. 
        case "exception" -> exception(request, response);
        // 오류 처리 방식: 잘못된 action 요청이 들어오면 404 에러를 발생
        //default -> forward(request, response, "/error/404.jsp");

        // TODO: 19. cart-form 요청을 확인하세요. 
        case "cart-form" -> forward(request, response, "/status/cart-form.jsp");

        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");

        // TODO: 18. add-to-cart, buy 요청을 확인하세요. 
        case "add-to-cart" -> addToCart(request, response);
        case "buy" -> buy(request, response);

        // 오류 처리 방식: 잘못된 action 요청이 들어오면 404 에러를 발생
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    private void makeCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 03. 다음을 만족시키는 쿠키를 생성해서 클라이언트에게 전송하고 /main?action=check-cookie에서 확인하세요. 
        // forward와 redirect 방식으로 각각 처리해보고 두 방식의 차이점을 고민해보자.
        setupCookie("just-10-min", "10분유지쿠키", 60 * 10, "/", response);
        setupCookie("just-1-min", URLEncoder.encode("1분 유지 쿠키", "UTF-8"), 60 * 1, null, response);
        setupCookie("status-path", "경로지정쿠키", -1, request.getContextPath() + "/member", response);
        setupCookie("immediate-del", "010-1234-5678", 0, null, response);
        redirect(request, response, "/main?action=check-cookie");

        // END
    }

    private void gugu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int dan = Integer.parseInt(request.getParameter("dan"));
            List<String> result = sService.getGugu(dan);
            request.setAttribute("result", result);
            // TODO: 05. 최근 찾아본 구구단의 목록을 recentGugu라는 이름으로 쿠키에 담아서 관리해보자.
            //  쿠키의 value로 사용할 수 있는 문자열로 구분자를 두자.

            // END
            forward(request, response, "/gugu/gugu-result.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("alertMsg", e.getMessage());
            forward(request, response, "/gugu/gugu-form.jsp");
        } catch (RuntimeException e) {
            e.printStackTrace();
            String query = URLEncoder.encode("구구단 " + request.getParameter("dan") + "단", "UTF-8");
            response.sendRedirect("https://www.google.com/search?q=" + query);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String product = request.getParameter("product");

        // TODO: 16. 파라미터인 product를 session의 cart attribute에 추가하세요.
        //  cart는 Map<String, Integer> 타입이다.
        //  처리 후 redirect로 /main?action=cart-form로 이동하세요.

        // END
    }

    private void buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: 20. session에 담긴 cart를 삭제하고 'alertMsg'를 키로 '구매 완료'를 전송하세요.
        //  처리 후 redirect로 /main?action=cart-form로 이동하세요.

        // END
    }

    private void exception(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int statusCode = Integer.parseInt(request.getParameter("status-code"));
        response.sendError(statusCode);
    }
}
