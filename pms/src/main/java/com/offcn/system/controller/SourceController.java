package com.offcn.system.controller;

import com.offcn.system.bean.Sources;
import com.offcn.system.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public List<Sources> getList(){
        List<Sources> list = sourceService.getListByPid(0);
        queryChildren(list.get(0));
        return list;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addSource(Sources sources){
        sourceService.addSource(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/getSourceById/{id}",method = RequestMethod.GET)
    public ModelAndView getSourceById(@PathVariable("id")Integer id){
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources sources = sourceService.getSourceById(id);
        mv.addObject("sources",sources);
        return mv;
    }

    @RequestMapping(value = "/updateSource",method = RequestMethod.PUT)
    public String updateSource(Sources sources){
        sourceService.updateSource(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/deleteSource/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteSourceById(@PathVariable("id") Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        boolean result = sourceService.deleteSourceById(id);
        if (result){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",499);
        }
        return map;
    }


    //通过顶部资源递归查询子资源
    public void queryChildren(Sources sources){
        List<Sources> list = sourceService.getListByPid(sources.getId());
        for (Sources sources1 : list) {
            queryChildren(sources1);
        }
        sources.setChildren(list);
    }

}
