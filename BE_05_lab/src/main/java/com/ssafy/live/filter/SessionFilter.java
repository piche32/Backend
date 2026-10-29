package com.ssafy.live.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// TODO: 12. /auth/* 경로에 대해 세션에 loginUser가 있는지 확인하는 필터를 작성하세요.
@WebFilter ("/auth/*")
public class SessionFilter extends HttpFilter{
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser") != null) {
			chain.doFilter(req, res);
		}
		else {
			session.setAttribute("alertMsg", "로그인 하고 오세요.");
		}
		res.sendRedirect(req.getContextPath()+"/member?action=login-form");
	}
}
// END
