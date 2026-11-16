package com.ssafy.live.interceptor;

import java.util.Arrays;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
// TODO: 06-1. HandlerInterceptor를 구현해서 요청 기존의 LoggingFilter를 대체해보자.
public class LoggingInterceptor implements HandlerInterceptor{
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
	        System.out.printf("##요청 경로: %s, 요청 방식: %s##\n", request.getRequestURI(), request.getMethod());
	        System.out.println("요청 파라미터 분석");
	        request.getParameterMap().forEach((k, v) -> {
	            System.out.printf(" - name: %s, value: %s\n", k, Arrays.toString(v));
	        });
	
	        long start = System.currentTimeMillis();
	        request.setAttribute("start", start);
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		long start = (long)request.getAttribute("start");
		System.out.printf("응답타입: %s, 응답상태: %d, 처리시간: %d\n", response.getContentType(), response.getStatus(), System.currentTimeMillis() - start);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}

//END
