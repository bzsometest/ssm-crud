package com.crud.bean;

public class Department {
    private Integer dept_Id;

    private String dept_name;
    
    public Department() {
	}

    public Department(Integer dept_Id, String dept_name) {
		super();
		this.dept_Id = dept_Id;
		this.dept_name = dept_name;
	}

	public Integer getDept_Id() {
        return dept_Id;
    }

    public void setDept_Id(Integer dept_Id) {
        this.dept_Id = dept_Id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name == null ? null : dept_name.trim();
    }
}