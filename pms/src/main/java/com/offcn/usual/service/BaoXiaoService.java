package com.offcn.usual.service;

import com.github.pagehelper.PageInfo;
import com.offcn.emp.bean.Employee;
import com.offcn.usual.bean.BaoXiao;

import java.util.List;

public interface BaoXiaoService {
    PageInfo<BaoXiao> getList(Employee loginUser, Integer pageNum);
}
