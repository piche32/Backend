package com.student.model.service;

import java.sql.SQLException;
import java.util.List;

import com.student.model.dao.MemberDAO;
import com.student.model.dao.MemberDAOImpl;
import com.student.model.dto.Student;

public class MemberServiceImpl implements MemberService{
	
	private MemberDAO dao = MemberDAOImpl.getInstance();
	
	static public MemberService instance = new MemberServiceImpl();
	private MemberServiceImpl() {	}
	static public MemberService getInstance() {
		return instance;
	}
	@Override
	public Student login(Student student) {
		try {
			return dao.login(student);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public List<Student> doList() {
		try {
			return dao.doList();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void doRegist(Student student) {
		try {
			dao.doRegist(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Student detail(String userId) {
		try {
			return dao.detail(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void update(Student student) {
		try {
			dao.update(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
