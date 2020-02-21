package com.offcn.pro.service;

import com.github.pagehelper.PageInfo;
import com.offcn.pro.bean.Project;

import java.util.List;

public interface ProjectService {
    boolean addProject(Project project);

    List<Project> projectList();

    boolean deleteProject(Integer[] ids);

    Project getProjectById(Integer id);

    void updateProject(Project project);

    List<Project> search(Integer cid, String keyword, Integer orderby);

    List<Project> projetListWithNotAnalysis();

    PageInfo<Project> getList(Integer pageNo);
}
