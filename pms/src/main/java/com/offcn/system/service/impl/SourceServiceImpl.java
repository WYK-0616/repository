package com.offcn.system.service.impl;

import com.offcn.system.bean.Sources;
import com.offcn.system.bean.SourcesExample;
import com.offcn.system.dao.SourcesMapper;
import com.offcn.system.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourcesMapper sourcesMapper;


    @Override
    public List<Sources> getListByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    @Override
    public void addSource(Sources sources) {
        sourcesMapper.insert(sources);
    }

    @Override
    public Sources getSourceById(Integer id) {
        Sources sources = sourcesMapper.selectByPrimaryKey(id);
        return sources;
    }

    @Override
    public void updateSource(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
    public boolean deleteSourceById(Integer id) {
        int i = sourcesMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    @Override
    public List<Sources> getSourcesByEid(Integer eid) {
        List<Sources> secondSources = sourcesMapper.getSourcesByEidAndPid(eid,1);
        for (Sources secondSource : secondSources) {
            List<Sources> thirdSources = sourcesMapper.getSourcesByEidAndPid(eid, secondSource.getId());
            secondSource.setChildren(thirdSources);
        }
        return secondSources;
    }
}
