package com.offcn.pro.controller;

import com.offcn.pro.bean.Module;
import com.offcn.pro.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> getModuleList(){
        List<Module> list = moduleService.getModuleList();
        return list;
    }

    @RequestMapping(value = "addModule",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addModule(Module module){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = moduleService.addModule(module);
        if (flag) {
            map.put("statusCode",200);
        } else {
            map.put("statusCode",499);
        }
        return map;
    }
    @RequestMapping(value = "/deleteModule",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteModule(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = moduleService.deleteModule(ids);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",499);
        }
        return map;
    }
    @RequestMapping(value = "/getModuleById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Module getModuleById(@PathVariable("id") Integer id){
        Module module = moduleService.getModuleById(id);
        return module;
    }
    @RequestMapping(value = "/updateModuleById",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> updateModuleById(Module module){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = moduleService.updateModuleById(module);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> search(Integer cid,String keyword,Integer orderby){
        List<Module> list = moduleService.search(cid,keyword,orderby);
        return list;
    }

    @RequestMapping(value = "/getModulesByAnalysisFk/{analysisFk}",method = RequestMethod.GET)
    @ResponseBody
    public List<Module> getModulesByAnalysisFk(@PathVariable("analysisFk") Integer analysisFk){
        List<Module> list = moduleService.getModulesByAnalysisFk(analysisFk);
        return list;
    }
}
