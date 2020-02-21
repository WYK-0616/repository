package com.offcn.pro.service;

import com.offcn.pro.bean.Function;

import java.util.List;

public interface FunctionService {

    List<Function> getList();

    boolean addFunction(Function function);

    boolean deleteFunction(Integer[] ids);

    Function getFunctionById(Integer id);

    boolean updateFunction(Function function);

    List<Function> search(Integer cid, String keyword, Integer orderby);
}
