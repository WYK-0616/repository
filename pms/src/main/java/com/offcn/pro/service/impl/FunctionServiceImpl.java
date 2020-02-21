package com.offcn.pro.service.impl;

import com.offcn.pro.bean.*;
import com.offcn.pro.bean.Module;
import com.offcn.pro.dao.AnalysisMapper;
import com.offcn.pro.dao.FunctionMapper;
import com.offcn.pro.dao.ModuleMapper;
import com.offcn.pro.dao.ProjectMapper;
import com.offcn.pro.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AnalysisMapper analysisMapper;
    @Autowired
    private ModuleMapper moduleMapper;

    public List<Function> getList() {
        FunctionExample example = new FunctionExample();
        List<Function> functions = functionMapper.selectByExample(example);
        for (Function function : functions) {
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(function.getProname()));
            function.setProject(project);
            Analysis analysis = analysisMapper.selectByPrimaryKey(Integer.parseInt(function.getAnalysisname()));
            function.setAnalysis(analysis);
            Module module = moduleMapper.selectByPrimaryKey(function.getModeleFk());
            function.setModule1(module);
        }
        return functions;
    }

    public boolean addFunction(Function function) {
        int insert = functionMapper.insert(function);
        return insert > 0;
    }

    public boolean deleteFunction(Integer[] ids) {
        FunctionExample example = new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = functionMapper.deleteByExample(example);
        return i == ids.length;
    }

    public Function getFunctionById(Integer id) {
        Function function = functionMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(function.getProname()));
        function.setProject(project);
        Analysis analysis = analysisMapper.selectByPrimaryKey(Integer.parseInt(function.getAnalysisname()));
        function.setAnalysis(analysis);
        Module module = moduleMapper.selectByPrimaryKey(function.getModeleFk());
        function.setModule1(module);
        return function;
    }

    public boolean updateFunction(Function function) {
        int i = functionMapper.updateByPrimaryKeySelective(function);
        return i > 0;
    }

    public List<Function> search(Integer cid, String keyword, Integer orderby) {
        List<Function> functions = functionMapper.search(cid,keyword,orderby);
        for (Function function : functions) {
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(function.getProname()));
            function.setProject(project);
            Analysis analysis = analysisMapper.selectByPrimaryKey(Integer.parseInt(function.getAnalysisname()));
            function.setAnalysis(analysis);
            Module module = moduleMapper.selectByPrimaryKey(function.getModeleFk());
            function.setModule1(module);
        }
        return functions;
    }
}
