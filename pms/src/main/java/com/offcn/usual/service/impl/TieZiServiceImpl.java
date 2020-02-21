package com.offcn.usual.service.impl;

import com.offcn.usual.bean.TieZi;
import com.offcn.usual.bean.TieZiExample;
import com.offcn.usual.dao.TieZiMapper;
import com.offcn.usual.service.TieZiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TieZiServiceImpl implements TieZiService {

    @Autowired
    private TieZiMapper tieZiMapper;

    @Override
    public void saveInfo(TieZi tieZi) {
        tieZi.setAddDate(new Date());
        tieZiMapper.insert(tieZi);
    }

    @Override
    public List<TieZi> getTieZiList() {
        TieZiExample example = new TieZiExample();
        example.setOrderByClause("add_date DESC");
        List<TieZi> tieZis = tieZiMapper.selectByExample(example);
        return tieZis;
    }
}
