package com.offcn.system.service.impl;

import com.offcn.system.bean.Role;
import com.offcn.system.bean.RoleExample;
import com.offcn.system.dao.RoleMapper;
import com.offcn.system.dao.RoleSourcesMapper;
import com.offcn.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleSourcesMapper roleSourcesMapper;
    @Override
    public Integer saveInfo(Role role) {
        roleMapper.insert(role);
        return role.getRoleid();
    }

    @Override
    public void save(Integer roleId, Integer[] sourcesId) {
        roleSourcesMapper.save(roleId,sourcesId);
    }

    @Override
    public List<Role> getList() {
        RoleExample example = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
    }
}
