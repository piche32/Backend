package com.ssafy.live.controller;

import java.io.IOException;

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
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionParameter(request, response);
        switch (action) {
        case "index" -> redirect(request, response, "/index.jsp");
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }


    private void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
