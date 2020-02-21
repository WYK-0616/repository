package com.offcn.pro.service;

import com.offcn.pro.bean.Module;

import java.util.List;

public interface ModuleService {
    List<Module> getModuleList();

    boolean addModule(Module module);

    boolean deleteModule(Integer[] ids);

    Module getModuleById(Integer id);

    boolean updateModuleById(Module module);

    List<Module> search(Integer cid, String keyword, Integer orderby);

    List<Module> getModulesByAnalysisFk(Integer analysisFk);
}
