package com.offcn.comparision.controller;

import com.offcn.comparision.bean.Comparision;
import com.offcn.comparision.service.ComparisionService;
import com.offcn.emp.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comparision")
public class ComparisionController {

    @Autowired
    private ComparisionService comparisionService;
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveInfo(Comparision comparision, HttpSession session){
        System.out.println(comparision);
        Map<String,Object> map = new HashMap<>();
        Employee emp = (Employee) session.getAttribute("loginUser");
        comparision.setEmpfk(emp.getEid());
        boolean result = comparisionService.saveInfo(comparision);
        if (result) {
            map.put("statusCode",200);
        } else {
            map.put("statusCode",499);
        }
        return map;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public List<Comparision> getList(){
        return comparisionService.getList();
    }
}
