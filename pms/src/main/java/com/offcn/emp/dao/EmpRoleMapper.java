package com.offcn.emp.dao;

import com.offcn.emp.bean.Employee;
import com.offcn.emp.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpRoleMapper {
   Integer addEmpRole(@Param("eid") Integer eid,@Param("rids") Integer[] rids);
}