package com.ssafy.live.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
@WebListener
public class MyContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("컨텍스트 초기화 중 - 모든 서블릿에서 사용하는 자원 초기화");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("컨텍스트 정리 중 - init 과정에서 초기화한 리소스 정리");
	}

	
}
