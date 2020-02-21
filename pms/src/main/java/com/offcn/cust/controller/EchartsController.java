package com.offcn.cust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @RequestMapping(value = "data",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getData(){
        Map<String,Object> map = new HashMap<>();
        map.put("安卓",3000);
        map.put("IOS",2000);
        map.put("windows",500);
        map.put("乌班图",100);
        System.out.println(map);
        return map;
    }
}
