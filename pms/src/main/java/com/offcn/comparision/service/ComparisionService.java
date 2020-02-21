package com.offcn.comparision.service;

import com.offcn.comparision.bean.Comparision;

import java.util.List;

public interface ComparisionService {
    boolean saveInfo(Comparision comparision);

    List<Comparision> getList();
}
