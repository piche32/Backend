package com.student.model.service;

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
	public Student login(com.student.model.dto.Student student) {
		return dao.login(student);
		
	}
	@Override
	public List<Student> doList() {
		return dao.doList();
	}
	@Override
	public void doRegist(Student student) {
		dao.doRegist(student);
	}
}
