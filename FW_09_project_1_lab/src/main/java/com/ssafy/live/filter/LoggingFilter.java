package com.ssafy.live.filter;

import java.io.IOException;
import java.util.Arrays;

import org.apache.ibatis.plugin.Intercepts;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter("/*")
//@SuppressWarnings("serial")
public class LoggingFilter extends HttpFilter implements Filter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.printf("##요청 경로: %s, 요청 방식: %s##\n", request.getRequestURI(), request.getMethod());
        System.out.println("요청 파라미터 분석");
        request.getParameterMap().forEach((k, v) -> {
            System.out.printf(" - name: %s, value: %s\n", k, Arrays.toString(v));
        });

        long start = System.currentTimeMillis();
       
        
        
        chain.doFilter(request, response);

        System.out.printf("응답타입: %s, 응답상태: %d, 처리시간: %d\n", response.getContentType(), response.getStatus(), System.currentTimeMillis() - start);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

}
