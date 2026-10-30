package com.student.model.dao;

import java.util.List;

import com.student.model.dto.Student;

public interface MemberDAO {

	public Student login(Student student);

	public List<Student> doList();

	public void doRegist(Student student);

}
