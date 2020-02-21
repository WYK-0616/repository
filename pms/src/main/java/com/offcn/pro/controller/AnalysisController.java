package com.offcn.pro.controller;

import com.offcn.pro.bean.Analysis;
import com.offcn.pro.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> getAnalysisList(){
        List<Analysis> list = analysisService.getAnalysisList();
        return list;
    }

    @RequestMapping(value = "/addAnalysis",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addAnalysis(Analysis analysis){
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        System.out.println(analysis);
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            boolean flag = analysisService.addAnalysis(analysis);
            if (flag){
                map.put("statusCode",200);
            } else {
                map.put("statusCode",500);
            }
        } catch (Exception ex){
            map.put("statusCode",499);
            ex.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/deleteByIds",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteByIds(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = analysisService.deleteByIds(ids);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }
    @RequestMapping(value = "/updateAnalysis",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> updateAnalysis(Analysis analysis){
        Map<String,Object> map = new HashMap<String, Object>();
        analysis.setUpdatetime(new Date());
        boolean flag = analysisService.updateAnalysis(analysis);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/getAnalysisById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Analysis getAnalysisById(@PathVariable("id") Integer id){
        Analysis analysis = analysisService.getAnalysisById(id);
        return analysis;
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> search(Integer cid,String keyword,Integer orderby){
        List<Analysis> list = analysisService.search(cid,keyword,orderby);
        return list;
    }

}
