package com.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.bean.Employee;
import com.crud.dao.DepartmentMapper;
import com.crud.dao.EmployeeMapper;
import com.curd.service.EmployeeService;

/**
 * 测试dao层的工作
 * 
 * @author chao
 * 
 *         推荐Spring项目就可以使用Spring单元测试，可以自动注入我们所需要的组件 1.导入SpringTest单元测试的包
 *         2.@ContextConfiguration指定Spring配置文件的位置 3.直接autowired即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperEmpTest2 {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;

	@Autowired
	EmployeeService employeeService;

	@Test
	public void testInsert() {
		/*
		 * // 1.创建SpringIOC容器 ApplicationContext ioc = new
		 * ClassPathXmlApplicationContext("applicationContext.xml"); // 2.从容器中获取mapper
		 * DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);
		 */
		System.out.println(departmentMapper);

		// 1.插入几个部门
		/*
		 * departmentMapper.insertSelective(new Department(6, "开发部"));
		 * departmentMapper.insertSelective(new Department(null, "测试部"));
		 */

		// 2.生成员工数据
		Employee employee = new Employee(null, "sssss222", "M", "ss@qq.com", 2);
		//employeeMapper.insertSelective(employee);
		employeeService.saveEmp(employee);
		System.out.println("批量操作完成");
	}
}
