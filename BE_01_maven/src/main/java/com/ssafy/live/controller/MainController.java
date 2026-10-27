package com.ssafy.live.controller;

import java.io.IOException;

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
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = getActionParameter(request);
		switch (action) {
		case "gugu" -> gugu(request, response);
		case "buy" -> buy(request, response);
		}
		
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = getActionParameter(request);
		switch (action) {
		case "login" -> login(request, response);
		case "write"-> write(request, response);
		}
	}
	
	protected void gugu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. param 분석
		int dan = Integer.parseInt(request.getParameter("dan"));
		//2. business logic 처리
		String content = "";
		for (int i = 0; i < 10; i++) {
			content += String.format("%d * %d = %d<br>", dan, i, dan * i);
		}
		//3. 화면 처리
		responseHtml("구구단" + dan + "단", content, response);
		
		
	}		
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. param 분석
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//2. business logic 처리
		String title = "로그인 실패";
		String content = "ID/PASS 확인 필요";
		if("ssafy".equals(id) && "1234".equals(pass)) {
			title = "환영합니다~!~!";
			content = "김싸피님 로그인 되었습니다.";
		}
		//3. 화면 처리
		responseHtml(title, content, response);
	}
	
	protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. param 분석
		String content = request.getParameter("content");
		//2. business logic 처리
		//3. 화면 처리
		responseHtml("글쓰기", content, response);
		
		
		
	}
	
	private long stock = 1;
	
	protected synchronized void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if(stock > 0) {
			//구매처리
			try{
				Thread.sleep(1000*10);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			stock--;
			responseHtml("판매성공", "남은 재고: " + stock, response);
		}else {
			responseHtml("판매 실패", "다 팔림", response);
			stock = 1;
		}
	}

	protected void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
