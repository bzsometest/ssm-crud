package com.crud.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class Employee {
	private Integer emp_Id;

	@Pattern(regexp = "(^[a-zA-Z0-9_-]{5,16}$)|(^[\u4e00-\u9fa5]{2,5})", message = "用户名必须是2-5位中文或者5-16位英文和数字的组合(S)")
	private String emp_name;

	private String gender;

	@Email(message = "邮箱格式不正确(S)")
	private String email;

	private Integer dept_id;

	public Employee() {
	}

	public Employee(Integer emp_Id, String emp_name, String gender, String email, Integer dept_id) {
		super();
		this.emp_Id = emp_Id;
		this.emp_name = emp_name;
		this.gender = gender;
		this.email = email;
		this.dept_id = dept_id;
	}

	// 希望查询员工的同时部门信息也是查询好的
	private Department department;

	public Integer getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(Integer emp_Id) {
		this.emp_Id = emp_Id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name == null ? null : emp_name.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "Employee [emp_Id=" + emp_Id + ", emp_name=" + emp_name + ", gender=" + gender + ", email=" + email
				+ ", dept_id=" + dept_id + ", department=" + department + "]";
	}
}