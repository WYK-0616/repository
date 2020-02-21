package com.offcn.usual.controller;

import com.offcn.usual.bean.TieZi;
import com.offcn.usual.service.TieZiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tz")
public class TieZiController {

    @Autowired
    private TieZiService tieZiService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String saveInfo(TieZi tieZi){
        tieZiService.saveInfo(tieZi);
        return "main";
    }

    @RequestMapping(value = "getTieZiList",method = RequestMethod.GET)
    @ResponseBody
    public List<TieZi> getTieZiList(){
        List<TieZi> list = tieZiService.getTieZiList();
        return list;
    }

}
