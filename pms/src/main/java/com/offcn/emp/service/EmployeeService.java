package com.offcn.emp.service;

import com.offcn.emp.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getManagerList();

    Employee loginValidate(String username, String password);

    Integer addEmp(Employee employee);

    boolean addRole(Integer eid,Integer[] rids);
}
