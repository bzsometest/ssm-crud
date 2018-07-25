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
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD() {
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
		employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "jjj@qq.ccom", 1));
		employeeMapper.insertSelective(new Employee(null, "mark", "F", "jmmm@qq.ccom", 2));

		// 3.批量插入多个员工；使用可以执行批量操作的sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 100; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
			mapper.insertSelective(new Employee(null, uid, "F", uid + "@qq.ccom", 2));
		}
		System.out.println("批量操作完成");
	}
}
