package com.offcn.pro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.cust.bean.Customer;
import com.offcn.cust.dao.CustomerMapper;
import com.offcn.emp.bean.Employee;
import com.offcn.emp.bean.EmployeeExample;
import com.offcn.emp.dao.EmployeeMapper;
import com.offcn.pro.bean.Project;
import com.offcn.pro.bean.ProjectExample;
import com.offcn.pro.dao.ProjectMapper;
import com.offcn.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Transactional
    public boolean addProject(Project project) {
        int insert = projectMapper.insert(project);
        return insert > 0;
    }

    public List<Project> projectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);

        for (Project project : projects) {
            Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
            project.setEmployee(employee);
            Customer customer = customerMapper.selectByPrimaryKey(project.getComname());
            project.setCustomer(customer);
        }

        return projects;
    }
    @Transactional
    public boolean deleteProject(Integer[] ids) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        List<Integer> list = Arrays.asList(ids);
        System.out.println(list);
        criteria.andPidIn(list);
        int result = projectMapper.deleteByExample(example);
        if (result == ids.length){
            return true;
        } else {
            return false;
        }
    }

    public Project getProjectById(Integer id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
        project.setEmployee(employee);
        Customer customer = customerMapper.selectByPrimaryKey(project.getComname());
        project.setCustomer(customer);
        return project;
    }

    @Transactional
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }


    public List<Project> search(Integer cid, String keyword, Integer orderby) {
        List<Project> projects = projectMapper.search(cid,keyword,orderby);
        return projects;
    }

    public List<Project> projetListWithNotAnalysis() {
        List<Project> list = projectMapper.projetListWithNotAnalysis();
        return list;
    }

    @Override
    public PageInfo<Project> getList(Integer pageNo) {
        PageHelper.startPage(pageNo,1);
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        PageInfo<Project> pageInfo = new PageInfo<>(projects);
        System.out.println(pageInfo.getList());
        return pageInfo;
    }
}
