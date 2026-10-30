package com.student.model.service;

import java.util.List;

import com.student.model.dto.Student;

public interface MemberService {

	public Student login(Student student);

	public List<Student> doList();
	
}
