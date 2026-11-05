package com.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.model.dto.Student;
import com.student.util.DBUtil;

public class MemberDAOImpl implements MemberDAO{

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
		
		String sql = "SELECT userid, userpw, username, score " //줄바꿈 시 빈칸 꼭 넣어주기!!!! 안그럼 합쳐져버림 <= sql 문장은 mysql에서 작동 잘 하는 지 확인하고 넣어두기
				+ "FROM student "
				+ "WHERE userid=? AND userpw=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student retStudent = null;
		try {
			conn = DBUtil.getConnection();//연결 맺기
			pstmt = conn.prepareStatement(sql); //preparedStatement 문 준비, 파라미터 값 세팅
			pstmt.setString(1, student.getUserid()); //첫 번째 ?에 넣을 문자열
			pstmt.setString(2, student.getUserpw()); //두 번째 ?에 넣을 문자열
			
			rs = pstmt.executeQuery(); //실행 (Select: executeQuery, 나머지: executeUpdate())
			//ResultSet도 커서부터 읽어옴 next() 함수 사용 => StringTokenizer과 비슷
			
			if (rs.next()) { //데이터가 있다면
				retStudent = new Student();
				retStudent.setUserid(rs.getString("userid"));
				retStudent.setUserpw(rs.getString("userpw"));
				retStudent.setUserName(rs.getString("username"));
				retStudent.setScore(rs.getInt("score"));
				System.out.println("DAO 로그인 조회 성공 : " + retStudent);
			}
		} //예외가 발생하면, 던져짐~~
		finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return retStudent;
	}
	
	
	@Override
	public List<Student> doList() throws SQLException {
		System.out.println("dao: list");
		String sql = "SELECT userid, userpw, username, score " + 
				"FROM student";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> retList = new ArrayList<Student>();;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student retStudent = new Student();
				retStudent.setUserid(rs.getString("userid"));
				retStudent.setUserpw(rs.getString("userpw"));
				retStudent.setUserName(rs.getString("username"));
				retStudent.setScore(rs.getInt("score"));
				retList.add(retStudent);
			}
			list = retList;
		}
		finally {
			DBUtil.close(rs, pstmt, conn); //객체를 생성한 것의 역순
		}
		return retList;
	}
	@Override
	public void doRegist(Student student) throws SQLException {
		System.out.println("dao: regist");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO student (userid, userpw, username, score) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getUserid());
			pstmt.setString(2, student.getUserpw());
			pstmt.setString(3, student.getUserName());
			pstmt.setInt(4, student.getScore());
			pstmt.executeUpdate();
			
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
	}
	@Override
	public Student detail(String userId) throws SQLException {
		System.out.println("dao: detail");
		Connection conn = null;
		PreparedStatement pstmt = null;
		Student retStudent = null;
		ResultSet rs = null;
		String sql = "SELECT userid, userpw, username, score "
				+ "FROM student "
				+ "WHERE userid=?";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				retStudent = new Student();
				retStudent.setUserid(rs.getString("userid"));
				retStudent.setUserpw(rs.getString("userpw"));
				retStudent.setUserName(rs.getString("username"));
				retStudent.setScore(rs.getInt("score"));
			}
			
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return retStudent;
	}
	@Override
	public void update(Student student) throws SQLException {
		System.out.println("dao: update");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE student "
				+ "SET userpw=?, username=?, score=? "
				+ "WHERE userid=?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getUserpw());
			pstmt.setString(2, student.getUserName());
			pstmt.setInt(3, student.getScore());
			pstmt.setString(4, student.getUserid());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
	}
}

//1. pom.xml jdbc 디펜던시 등록, 메이븐 업데이트 <= 프로젝트에서 한번만 설정하면 됨
//2. 드라이버 로딩 프로그램에서 한 번만 하면 됨 => static {} => static 초기화 블럭에 작성 <= 실행될 때 한 번만 하면 됨