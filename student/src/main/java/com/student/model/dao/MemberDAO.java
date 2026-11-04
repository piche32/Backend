package com.student.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.student.model.dto.Student;

public interface MemberDAO {

	public Student login(Student student) throws SQLException;

	public List<Student> doList() throws SQLException;

	public void doRegist(Student student) throws SQLException;

}
