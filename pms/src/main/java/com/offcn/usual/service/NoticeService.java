package com.offcn.usual.service;

import com.offcn.usual.bean.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getLimitList();

    List<Notice> getList();
}
