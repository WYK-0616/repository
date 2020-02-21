package com.offcn.usual.dao;

import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.bean.BaoXiaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaoXiaoMapper {
    long countByExample(BaoXiaoExample example);

    int deleteByExample(BaoXiaoExample example);

    int deleteByPrimaryKey(String bxid);

    int insert(BaoXiao record);

    int insertSelective(BaoXiao record);

    List<BaoXiao> selectByExample(BaoXiaoExample example);

    BaoXiao selectByPrimaryKey(String bxid);

    int updateByExampleSelective(@Param("record") BaoXiao record, @Param("example") BaoXiaoExample example);

    int updateByExample(@Param("record") BaoXiao record, @Param("example") BaoXiaoExample example);

    int updateByPrimaryKeySelective(BaoXiao record);

    int updateByPrimaryKey(BaoXiao record);
}