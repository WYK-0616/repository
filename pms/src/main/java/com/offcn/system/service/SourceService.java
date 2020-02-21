package com.offcn.system.service;

import com.offcn.system.bean.Sources;

import java.util.List;

public interface SourceService {

    List<Sources> getListByPid(int i);

    void addSource(Sources sources);

    Sources getSourceById(Integer id);

    void updateSource(Sources sources);

    boolean deleteSourceById(Integer id);

    List<Sources> getSourcesByEid(Integer eid);
}
