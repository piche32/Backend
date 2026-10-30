package com.student.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.student.model.dto.Student;

public class MemberDAOImpl implements MemberDAO{
	private List<Student> list = new ArrayList<>();
	
	private MemberDAOImpl() {
		list.add(new Student("ssafy", "1234", "ㅂㅅㅎ", 100));
		list.add(new Student("pshpsh", "2345", "ㅂㅅㅎ11", 100));
	}
	static public MemberDAO instance = new MemberDAOImpl();
	static public MemberDAO getInstance() {
		return instance;
	}
	@Override
	public Student login(Student student) {
		System.out.println("dao : login");
		synchronized (list) {
			return list.stream().filter(s->s.getUserid().equals(student.getUserid()) 
					&& s.getUserpw().equals(student.getUserpw()))
			.findFirst()
			.orElse(null);
		}
	}
	@Override
	public List<Student> doList() {
		synchronized (list) {
			return list;
		}
	}
}
