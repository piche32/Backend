package com.student.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.student.model.dto.Student;
import com.student.model.service.MemberService;
import com.student.model.service.MemberServiceImpl;

/**
 * Servlet implementation class memberController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static MemberService mService = MemberServiceImpl.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8"); // 한글 깨지는 부분에 대한 설정
		System.out.println("doPost");
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doProcess");
		String menu = request.getParameter("mymenu");
		System.out.println("mymenu: " + menu);
		
		RequestDispatcher disp;
		switch (menu) {
		case "login_form":  //단순 화면 이동
			disp = request.getRequestDispatcher("/login_form.jsp");
			disp.forward(request, response);
			break;
			
		case "regist_form":
			request.getRequestDispatcher("/regist_form.jsp").forward(request, response);
			break;
		
		case "login":
			doLogin(request, response);
			break;
		case "list":
			doList(request, response);
			break;
			
		case "logout":
			doLogout(request, response);
			break;
		case "regist":
			doRegist(request, response);
		default:
			break;
		}
	}
	
	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String userName = request.getParameter("userName");
		int score = Integer.parseInt(request.getParameter("score"));
		
		Student student = new Student(userid, userpw, userName, score);
		mService.doRegist(student);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginStudent", student.getUserName());
		response.sendRedirect(request.getContextPath() + "/member?mymenu=list"); //직접 doList를 호출하면 안 된다. 이렇게 컨트롤러를 거쳐서 가야 StudentList를 얻어서 갈 수 있다.
	
	}
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	/**DB에서 회원목록 가져와서 데이터 담아서 list.jsp를 forward 호출 **/
	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doList()");
		List<Student> list = mService.doList();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("doLogin()");
		//request에서 파라미터 꺼내기, DTO에 담기, Service 호출, DAO 호출 리턴. Controller에서 jsp 호출
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		System.out.println(userid + ", " + userpw);
		Student student = new Student(userid, userpw, "", 0);
		Student resStudent = mService.login(student);
		if(resStudent != null) {
			System.out.println("로그인 성공");
			//세션의 사용자 정보 담고, list로 보내자
			HttpSession session = request.getSession();
			session.setAttribute("loginStudent", resStudent.getUserName());
			response.sendRedirect(request.getContextPath() + "/member?mymenu=list"); //직접 doList를 호출하면 안 된다. 이렇게 컨트롤러를 거쳐서 가야 StudentList를 얻어서 갈 수 있다.
		}
		else {
			System.out.println("로그인 실패");
			//메시지 담아서 login_form.jsp로 포워드로 가자
			request.setAttribute("alertMsg", "로그인 실패, id/pw 확인하세요.");
			RequestDispatcher disp = request.getRequestDispatcher("/login_form.jsp");
			disp.forward(request, response);
		}
	}

}