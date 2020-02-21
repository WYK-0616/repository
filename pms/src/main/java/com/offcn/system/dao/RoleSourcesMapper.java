package com.offcn.system.dao;

import org.apache.ibatis.annotations.Param;

public interface RoleSourcesMapper {

    void save(@Param("rid") Integer roleId, @Param("sourcesId") Integer[] sourcesId);
}
