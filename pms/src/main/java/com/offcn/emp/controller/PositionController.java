package com.offcn.emp.controller;

import com.offcn.emp.bean.Position;
import com.offcn.emp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> getList(){
        return positionService.getList();
    }
}
