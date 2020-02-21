package com.offcn.emp.service.impl;

import com.offcn.emp.bean.Position;
import com.offcn.emp.bean.PositionExample;
import com.offcn.emp.dao.PositionMapper;
import com.offcn.emp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public List<Position> getList() {
        PositionExample example = new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        return positions;
    }
}
