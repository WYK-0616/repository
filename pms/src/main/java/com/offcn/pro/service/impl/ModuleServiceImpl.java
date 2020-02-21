package com.offcn.pro.service.impl;

import com.offcn.pro.bean.Analysis;
import com.offcn.pro.bean.Module;
import com.offcn.pro.bean.ModuleExample;
import com.offcn.pro.bean.Project;
import com.offcn.pro.dao.AnalysisMapper;
import com.offcn.pro.dao.ModuleMapper;
import com.offcn.pro.dao.ProjectMapper;
import com.offcn.pro.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AnalysisMapper analysisMapper;

    public List<Module> getModuleList() {
        ModuleExample example = new ModuleExample();
        List<Module> modules = moduleMapper.selectByExample(example);
        for (Module module :modules) {
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(module.getProname()));
            module.setProject(project);
            Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
            module.setAnalysis(analysis);
        }
        return modules;
    }

    public boolean addModule(Module module) {
        int insert = moduleMapper.insert(module);
        return insert > 0;
    }

    public boolean deleteModule(Integer[] ids) {
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = moduleMapper.deleteByExample(example);
        return i == ids.length;
    }

    public Module getModuleById(Integer id) {
        Module module = moduleMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(module.getProname()));
        module.setProject(project);
        Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
        module.setAnalysis(analysis);
        return module;
    }

    public boolean updateModuleById(Module module) {
        int i = moduleMapper.updateByPrimaryKey(module);
        return i > 0;
    }

    public List<Module> search(Integer cid, String keyword, Integer orderby) {
        List<Module> list = moduleMapper.search(cid,keyword,orderby);
        for (Module module :list) {
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(module.getProname()));
            module.setProject(project);
            Analysis analysis = analysisMapper.selectByPrimaryKey(module.getAnalysisFk());
            module.setAnalysis(analysis);
        }
        return list;
    }

    public List<Module> getModulesByAnalysisFk(Integer analysisFk) {
        ModuleExample example = new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(analysisFk);
        List<Module> list = moduleMapper.selectByExample(example);
        return list;
    }
}
