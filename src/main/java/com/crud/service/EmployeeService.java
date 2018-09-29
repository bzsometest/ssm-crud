package com.crud.service;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import com.crud.bean.EmployeeExample.Criteria;
import com.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     *
     * @return
     */
    public List<Employee> getAll() {
        // TODO Auto-generated method stub

        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 员工保存
     *
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检查用户名是否可用
     *
     * @param empName
     * @return
     */
    public boolean checkEmpName(String empName) {
        // TODO Auto-generated method stub
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmp_nameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 按照员工id查询员工
     *
     * @param emp_id
     * @return
     */
    public Employee getEmpById(Integer emp_id) {
        return employeeMapper.selectByPrimaryKey(emp_id);
    }

    /**
     * 更新员工数据
     *
     * @param employee
     */
    public void updateEmp(Employee employee) {
        // TODO Auto-generated method stub
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除员工
     *
     * @param emp_id
     */
    public void deleteEmpById(Integer emp_id) {
        employeeMapper.deleteByPrimaryKey(emp_id);
    }

    /**
     * 批量删除员工
     *
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample employeeExample = new EmployeeExample();
        Criteria criteria = employeeExample.createCriteria();
        criteria.andEmp_IdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}
