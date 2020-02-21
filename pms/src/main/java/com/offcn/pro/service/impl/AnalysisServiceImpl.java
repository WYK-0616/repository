package com.offcn.pro.service.impl;

import com.offcn.pro.bean.Analysis;
import com.offcn.pro.bean.AnalysisExample;
import com.offcn.pro.bean.ProjectExample;
import com.offcn.pro.dao.AnalysisMapper;
import com.offcn.pro.dao.ProjectMapper;
import com.offcn.pro.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    @Autowired
    private ProjectMapper projectMapper;

    public List<Analysis> getAnalysisList() {
        AnalysisExample example = new AnalysisExample();
        List<Analysis> analyses = analysisMapper.selectByExample(example);
        return analyses;
    }

    public boolean addAnalysis(Analysis analysis) {
        int insert = analysisMapper.insert(analysis);
        return insert > 0;
    }

    public boolean deleteByIds(Integer[] ids) {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int result = analysisMapper.deleteByExample(example);
        return result == ids.length;
    }

    public Analysis getAnalysisById(Integer id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        return analysis;
    }

    public boolean updateAnalysis(Analysis analysis) {
        int result = analysisMapper.updateByPrimaryKeySelective(analysis);
        return result > 0;
    }

    public List<Analysis> search(Integer cid, String keyword, Integer orderby) {
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        if (cid == 0){
            criteria.andPronameLike("%"+ keyword +"%");
            AnalysisExample.Criteria criteria1 = example.createCriteria();
            criteria1.andTitleLike("%"+ keyword +"%");
            example.or(criteria1);
        } else if (cid == 1){
            criteria.andPronameLike("%"+ keyword +"%");
        } else {
            criteria.andTitleLike("%"+ keyword +"%");
        }

        if (orderby == 1){
            example.setOrderByClause("addtime");
        } else {
            example.setOrderByClause("addtime");
        }
        List<Analysis> list = analysisMapper.selectByExample(example);
        return list;
    }
}
