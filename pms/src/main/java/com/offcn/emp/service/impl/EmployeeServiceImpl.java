package com.offcn.emp.service.impl;

import com.offcn.emp.bean.Employee;
import com.offcn.emp.bean.EmployeeExample;
import com.offcn.emp.dao.EmpRoleMapper;
import com.offcn.emp.dao.EmployeeMapper;
import com.offcn.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmpRoleMapper empRoleMapper;

    public List<Employee> getManagerList() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    public Employee loginValidate(String username, String password) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees.get(0);
    }

    @Override
    public Integer addEmp(Employee employee) {
        employeeMapper.insert(employee);
        return employee.getEid();
    }

    @Override
    public boolean addRole(Integer eid,Integer[] rids) {
        System.out.println(eid);
        empRoleMapper.addEmpRole(eid,rids);
        return false;
    }
}
