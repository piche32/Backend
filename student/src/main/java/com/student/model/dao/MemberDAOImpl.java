package com.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.model.dto.Student;

public class MemberDAOImpl implements MemberDAO{
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssafyb3?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	//jdbc:mysql => https 대신 씀, 127.0.0.1:3306 => 처음에 mysql 다운 받으면서 셋팅했던 주소, ssafyb3 => 데이터베이스 생성했던 이름!!!! 뒤에는 뭐 몰라도 됨
	private static final String USER = "ssafy";
	private static final String PASSWORD = "ssafy";
	static {
		try {
			Class.forName(DRIVER); //드라이버 로딩
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private List<Student> list = new ArrayList<>();
	
	private MemberDAOImpl() {
	}
	static public MemberDAO instance = new MemberDAOImpl();
	static public MemberDAO getInstance() {
		return instance;
	}
	@Override
	public Student login(Student student) throws SQLException {
		System.out.println("dao : login");
		
		String sql = "SELECT userid, userpw, username, score " //줄바꿈 시 빈칸 꼭 넣어주기!!!! 안그럼 합쳐져버림
				+ "FROM student "
				+ "WHERE userid=? AND userpw=?";
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); //연결 맺기
		PreparedStatement pstmt = conn.prepareStatement(sql); //preparedStatement 문 준비, 파라미터 값 세팅
		pstmt.setString(1, student.getUserid()); //첫 번째 ?에 넣을 문자열
		pstmt.setString(2, student.getUserpw()); //두 번째 ?에 넣을 문자열
		
		ResultSet rs = pstmt.executeQuery(); //실행 (Select: executeQuery, 나머지: executeUpdate())
		
		return null;
	}
	@Override
	public List<Student> doList() {
		return null;
	}
	@Override
	public void doRegist(Student student) {
		
	}
}

//1. pom.xml jdbc 디펜던시 등록
//2. 드라이버 로딩 프로그램에서 한 번만 하면 됨 => static {} => static 초기화 블럭에 작성