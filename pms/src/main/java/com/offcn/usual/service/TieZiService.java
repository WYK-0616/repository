package com.offcn.usual.service;

import com.offcn.usual.bean.TieZi;

import java.util.List;

public interface TieZiService {

    void saveInfo(TieZi tieZi);

    List<TieZi> getTieZiList();
}
