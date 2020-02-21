package com.offcn.comparision.dao;

import com.offcn.comparision.bean.Comparision;
import com.offcn.comparision.bean.ComparisionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComparisionMapper {
    long countByExample(ComparisionExample example);

    int deleteByExample(ComparisionExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Comparision record);

    int insertSelective(Comparision record);

    List<Comparision> selectByExample(ComparisionExample example);

    Comparision selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Comparision record, @Param("example") ComparisionExample example);

    int updateByExample(@Param("record") Comparision record, @Param("example") ComparisionExample example);

    int updateByPrimaryKeySelective(Comparision record);

    int updateByPrimaryKey(Comparision record);
}