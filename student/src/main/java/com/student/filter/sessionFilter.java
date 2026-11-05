package com.student.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/member")
public class sessionFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		String menu = request.getParameter("mymenu");
//		if(menu == null || !menu.equals("list")) {
//			System.out.println(menu);
//			chain.doFilter(request, response);
//			return;
//		}
//		HttpSession session = request.getSession();
//		if(session.getAttribute("loginStudent") != null) {
//			System.out.println("세션 통과");
//			chain.doFilter(request, response);
//		} else {
//			session.setAttribute("alertMsg", "로그인 하고 오세요.");
//			response.sendRedirect(request.getContextPath() + "/member?mymenu=login_form");
//			System.out.println("로그인 하고 오소");
//		}
		
		//우리 프로젝트 구조상 할 수 없는 코드
	}
}
