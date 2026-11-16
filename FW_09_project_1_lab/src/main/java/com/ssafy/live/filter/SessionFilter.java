package com.ssafy.live.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebFilter({"/auth"})
//@SuppressWarnings("serial")
public class SessionFilter extends HttpFilter implements Filter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") != null) {
            chain.doFilter(request, response);
        } else {
            session.setAttribute("alertMsg", "로그인 후 사용하세요.");
            res.sendRedirect(request.getContextPath() + "/member/login-form");
        }
    }
}
