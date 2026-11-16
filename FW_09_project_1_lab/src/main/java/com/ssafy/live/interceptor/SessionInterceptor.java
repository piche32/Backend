package com.ssafy.live.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
// TODO: 07-1. 기존의 SessionFilter를 대체해보자.
public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") != null) {
        	return true;
        } else {
            session.setAttribute("alertMsg", "로그인 후 사용하세요.");
            response.sendRedirect(request.getContextPath() + "/member/login-form");
        }
		return false;
	}
}

// END
