package com.ssafy.live.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public interface ControllerHelper {
    default String getActionParameter(HttpServletRequest request, HttpServletResponse response) {
        // action 파라미터를 추출하여 반환
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "index";
        }
        System.out.println("action: " + action);
        return action;
    }

    public default void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        if (path.startsWith("http")) {
            response.sendRedirect(path);
        } else {
            response.sendRedirect(request.getContextPath() + path);
        }
    }

    public default void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(path);
        disp.forward(request, response);
    }

    public default void setupCookie(String name, String value, int maxAge, String path, HttpServletResponse resp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (path != null) {
            cookie.setPath(path);
        }
        resp.addCookie(cookie);
    }

    public default void toJSON(Object target, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 02. JSON 응답을 위한 메소드를 작성해보자.

        // END
    }
}
