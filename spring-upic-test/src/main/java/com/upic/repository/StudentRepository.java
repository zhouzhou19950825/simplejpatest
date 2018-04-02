package com.upic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.upic.po.Student;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{

	public List<Student> findByUserName(String username);
	
	/**
	 * 分页
	 * @param username
	 * @param page
	 * @return
	 */
	public Page<Student> findByUserName(String username,Pageable page);
}
