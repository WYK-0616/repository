package com.offcn.pro.controller;

import com.offcn.pro.bean.Function;
import com.offcn.pro.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> getList(){
        List<Function> list = functionService.getList();
        return list;
    }

    @RequestMapping(value = "/addFunction",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addFunction(Function function){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = functionService.addFunction(function);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/deleteFunction",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteFunction(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = functionService.deleteFunction(ids);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/getFunctionById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Function getFunctionById(@PathVariable("id") Integer id){
        Function function = functionService.getFunctionById(id);
        return function;
    }

    @RequestMapping(value = "/updateFunction",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> updateFunction(Function function){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = functionService.updateFunction(function);
        if (flag) {
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> search(Integer cid,String keyword,Integer orderby){
        List<Function> list = functionService.search(cid,keyword,orderby);
        return list;
    }

}
