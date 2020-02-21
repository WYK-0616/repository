package com.offcn.usual.controller;

import com.github.pagehelper.PageInfo;
import com.offcn.emp.bean.Employee;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.service.BaoXiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bx")
public class BaoXiaoController {
    @Autowired
    private BaoXiaoService baoXiaoService;

    @RequestMapping("/list")
    public ModelAndView getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        PageInfo<BaoXiao> pageInfo = baoXiaoService.getList(loginUser,pageNum);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }
}
