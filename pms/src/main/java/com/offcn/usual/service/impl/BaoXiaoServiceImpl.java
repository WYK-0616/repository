package com.offcn.usual.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.emp.bean.Employee;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.bean.BaoXiaoExample;
import com.offcn.usual.dao.BaoXiaoMapper;
import com.offcn.usual.service.BaoXiaoService;
import com.offcn.utils.PagingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaoXiaoServiceImpl implements BaoXiaoService {
    @Autowired
    private BaoXiaoMapper baoXiaoMapper;

    public PageInfo<BaoXiao> getList(Employee loginUser,Integer pageNum) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(loginUser.getEid());
        PageHelper.startPage(pageNum, 1);
        List<BaoXiao> list = baoXiaoMapper.selectByExample(example);
        PageInfo<BaoXiao> pageInfo = new PageInfo<BaoXiao>(list,5);
        return pageInfo;
    }
}
