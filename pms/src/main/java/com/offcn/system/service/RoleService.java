package com.offcn.system.service;

import com.offcn.system.bean.Role;

import java.util.List;

public interface RoleService {
    Integer saveInfo(Role role);

    void save(Integer roleId, Integer[] sourcesId);

    List<Role> getList();
}
