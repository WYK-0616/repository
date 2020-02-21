package com.offcn.pro.service;

import com.offcn.pro.bean.Analysis;

import java.util.List;

public interface AnalysisService {

    List<Analysis> getAnalysisList();

    boolean addAnalysis(Analysis analysis);

    boolean deleteByIds(Integer[] ids);

    Analysis getAnalysisById(Integer id);

    boolean updateAnalysis(Analysis analysis);

    List<Analysis> search(Integer cid, String keyword, Integer orderby);
}
