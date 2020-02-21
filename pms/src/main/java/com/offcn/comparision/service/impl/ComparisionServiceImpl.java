package com.offcn.comparision.service.impl;

import com.offcn.comparision.bean.Comparision;
import com.offcn.comparision.bean.ComparisionExample;
import com.offcn.comparision.dao.ComparisionMapper;
import com.offcn.comparision.service.ComparisionService;
import com.offcn.emp.bean.Employee;
import com.offcn.emp.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparisionServiceImpl implements ComparisionService {

    @Autowired
    private ComparisionMapper comparisionMapper;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public boolean saveInfo(Comparision comparision) {
        int insert = comparisionMapper.insert(comparision);
        return insert > 0;
    }

    @Override
    public List<Comparision> getList() {
        ComparisionExample example = new ComparisionExample();
        List<Comparision> list = comparisionMapper.selectByExample(example);
        for(int i = 0;i< list.size();i++){
            Integer eid = list.get(i).getEmpfk();
            Employee employee = employeeMapper.selectByPrimaryKey(eid);
            list.get(i).setEmployee(employee);
        }
        return list;
    }
}
