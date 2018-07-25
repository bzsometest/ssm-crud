package com.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.bean.Department;
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
public class AutoWiredTest {

	@Autowired
	Department department;

	@Test
	public void testWired() {
	department.setDept_name("ceshi");
	System.out.println(department.getDept_name());
	}
}
