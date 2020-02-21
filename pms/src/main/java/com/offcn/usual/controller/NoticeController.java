package com.offcn.usual.controller;

import com.offcn.usual.bean.Notice;
import com.offcn.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/getLimitList",method = RequestMethod.GET)
    @ResponseBody
    public List<Notice> getLimitList(){
        return noticeService.getLimitList();
    }
    @RequestMapping(value = "showAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Notice> showAll(){
        return noticeService.getList();
    }
}
