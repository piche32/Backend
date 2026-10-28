package com.ssafy.live.filter;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebFilter("/auth/*")
public class SessionFilter extends HttpFilter{
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        System.out.println(session.getAttribute("loginUser"));
        if(session.getAttribute("loginUser")!=null) {
            chain.doFilter(req, res);
        }else {
            session.setAttribute("alertMsg", "로그인 후 사용하세요.");
            res.sendRedirect(req.getContextPath() + "/member?action=login-form");
        }
    }
}
