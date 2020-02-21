package com.offcn.pro.controller;

import com.github.pagehelper.PageInfo;
import com.offcn.emp.bean.Employee;
import com.offcn.pro.bean.Project;
import com.offcn.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //添加新项目
    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProject(@RequestBody Project project, HttpSession session){
        Employee employee =(Employee) session.getAttribute("loginUser");
        project.setEmpFk(employee.getEid());
        boolean flag = projectService.addProject(project);
        System.out.println(project);
        Map<String,Object> map = new HashMap<String, Object>();
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",1000);
        }
        return map;
    }

    //查询项目列表
    @RequestMapping(value = "/projetList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> projectList(){
        List<Project> list = projectService.projectList();
        return list;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Project> getList(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo){
        PageInfo<Project> pageInfo = projectService.getList(pageNo);
        return pageInfo;
    }

    //删除项目
    @RequestMapping(value = "/deleteProject",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteProject(@RequestParam("ids[]") Integer[] ids){
        boolean flag = projectService.deleteProject(ids);
        System.out.println(flag);
        Map<String,Object> map = new HashMap<String, Object>();
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",599);
        }
        return map;
    }

    //修改项目时的数据回显
    @RequestMapping(value = "/getProjectById/{id}",method = RequestMethod.GET)
    public ModelAndView getProjectById(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("project-base-edit");
        Project project = projectService.getProjectById(id);
        mv.addObject("project",project);
        return mv;
    }
    //显示项目详情
    @RequestMapping(value = "/getProjectById2/{id}",method = RequestMethod.GET)
    public ModelAndView getProjectById2(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("project-base-look");
        Project project = projectService.getProjectById(id);
        mv.addObject("project",project);
        return mv;
    }

    //修改项目
    @RequestMapping(value = "/updateProject",method = RequestMethod.PUT)
    public String updateProject(Project project){
        System.out.println(project);
        projectService.updateProject(project);
        return "redirect:/project-base.jsp";
    }

    //根据条件搜索项目
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> search(Integer cid,String keyword,Integer orderby){
        System.out.println(cid+keyword+orderby);
        List<Project> list = projectService.search(cid,keyword,orderby);
        return list;
    }

    @RequestMapping(value = "/projetListWithNotAnalysis",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> projetListWithNotAnalysis(){
        List<Project> list = projectService.projetListWithNotAnalysis();
        return list;
    }
}
