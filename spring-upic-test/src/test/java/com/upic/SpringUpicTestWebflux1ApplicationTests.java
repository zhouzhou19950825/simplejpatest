package com.upic;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.upic.po.Student;
import com.upic.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringUpicTestWebflux1Application.class)
public class SpringUpicTestWebflux1ApplicationTests {

	@Autowired
	private StudentRepository studentDao;
	
	/**
	 * 新建学生
	 */
	@Test
	public void saveStudent() {
		Student s=new Student();
		s.setUserName("123");
		s.setPassword("1232");
		Student save = studentDao.save(s);
		System.out.println(save.getId());
	}

	/**
	 * 查询学生
	 */
	@Test
	public void searchStudentById() {
		Student student = studentDao.findOne(1L);
//		Student one = studentDao.getOne(1L);
		System.out.println(student.getUserName());
	}
	/**
	 * save&saveflush
	 */
	@Test
	public void flushStudent() {
		Student student = studentDao.findOne(1L);
//		Student one = studentDao.getOne(1L);
		student.setUserName("321");
		student=studentDao.save(student);
		System.out.println(student.getUserName());
		
	    student = studentDao.findOne(1L);
	    student.setUserName("111");
	    student = studentDao.saveAndFlush(student);
	    System.out.println(student.getUserName());
	    System.out.println(student.getId());
	}
	
	
	/**
	 * 查询多个异常
	 */
	@Test
	public void searchMoreError() {
		List<Student> findByUserName = studentDao.findByUserName("123");
		findByUserName.forEach(System.out::println);
	}
	
	/**
	 * 删除
	 */
	@Test
	public void delete() {
		studentDao.delete(3L);
	}
	
	/**
	 * 切记 pagerequest 第一个参数是页数是从0开始，第二个参数条数
	 * Page对象中的getContent()方法则是获取List对象
	 */
	@Test
	public void testPageable() {
		Page<Student> findAll = studentDao.findAll(new PageRequest(0, 10));
		
		//第二种自定义分页
		Page<Student> findByUserName = studentDao.findByUserName("", new PageRequest(0, 10));
	}
}
