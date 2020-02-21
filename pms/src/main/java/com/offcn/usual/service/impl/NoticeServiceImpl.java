package com.offcn.usual.service.impl;

import com.offcn.usual.bean.Notice;
import com.offcn.usual.bean.NoticeExample;
import com.offcn.usual.dao.NoticeMapper;
import com.offcn.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    public List<Notice> getLimitList() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 0,3");
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    public List<Notice> getList() {
        NoticeExample example = new NoticeExample();
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }
}
