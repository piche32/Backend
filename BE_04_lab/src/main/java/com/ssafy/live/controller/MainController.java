package com.ssafy.live.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.ssafy.live.model.service.BasicSimpleService;
import com.ssafy.live.model.service.SimpleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        // 오류 처리 방식: 잘못된 action 요청이 들어오면 404 에러를 발생
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getActionParameter(req, resp);
        switch (action) {
        }
    }

    private void gugu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int dan = Integer.parseInt(request.getParameter("dan"));
            List<String> result = sService.getGugu(dan);
            request.setAttribute("result", result);
            forward(request, response, "/gugu/gugu-result.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("≈", e.getMessage());
            forward(request, response, "/gugu/gugu-form.jsp");
        } catch (RuntimeException e) {
            e.printStackTrace();
            String query = URLEncoder.encode("구구단 " + request.getParameter("dan") + "단", "UTF-8");
            response.sendRedirect("https://www.google.com/search?q=" + query);
        }
    }
}
