package com.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.bean.Employee;
import com.crud.bean.Msg;
import com.curd.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工CRUD请求
 * 
 * @author chao
 * 
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * 更新员工数据 ajax直接PUT请求会拿不到数据  需要在web.xml配置过滤器HttpPutFormContentFilter<
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{emp_Id}", method = RequestMethod.PUT)
	public Msg updateEmp(@Valid Employee employee, BindingResult result, HttpServletRequest request) {
		// employee中未设置id,如要设置参数中必须与id属性相同

		System.out.println("请求体中的值：" + request.getParameter("gender"));
		System.out.println("updateEmp将要更新的值：" + employee.toString());
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				System.out.println("错误字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFileds", map);
		} else {
			employeeService.updateEmp(employee);
			return Msg.success();
		}
	}

	/**
	 * 根据id查询员工信息
	 * 
	 * @param emp_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{emp_id}", method = RequestMethod.GET)
	public Msg getEmp(@PathVariable("emp_id") Integer emp_id) {// 指明从路径中获得id
		Employee employee = employeeService.getEmpById(emp_id);
		return Msg.success().add("emp", employee);
	}

	/**
	 * 检验用户名是否可用
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkuser")
	public Msg checkEmpName(String emp_name) {
		boolean b = employeeService.checkEmpName(emp_name);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail();
		}

	}

	/**
	 * 保存员工数据 1，支持JSR303校验 2，导入Hibernate-Validator
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				System.out.println("错误字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFileds", map);
		} else {
			System.out.println(employee.toString());
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}

	/**
	 * 查询员工数据（分页查询） 需要导入jackson包
	 * 
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper
		// 在查询之前只需要调用，传入页面，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后紧跟的查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageinfo包装查询后的结果
		// 封装了详细的分页信息，包括我们查询出来的数据，连续现实的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping("/empsJsp")
	public String getEmpsJsp(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 引入PageHelper
		// 在查询之前只需要调用，传入页面，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后紧跟的查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageinfo包装查询后的结果
		// 封装了详细的分页信息，包括我们查询出来的数据，连续现实的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		model.addAttribute("pageInfo", page);
		return "list";
	}

	@RequestMapping("/test")
	@ResponseBody
	public Map<String, Object> getTest(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> map2 = new HashMap<String, String>();
		map.put("ccc", "ccc");
		map.put("cc2c", "ccc2");
		map2.put("啊啊啊", "撒的发生的 ");
		map2.put("啊2啊啊", "撒的发生的2 ");
		map.put("map", map2);
		return map;
	}

}
