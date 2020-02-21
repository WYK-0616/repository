package com.offcn.system.controller;

import com.offcn.system.bean.Role;
import com.offcn.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveInfo(Role role,Integer[] sourcesId){

        //1、先往Role表中添加数据，拿到主键
        Integer roleId = roleService.saveInfo(role);
        System.out.println(Arrays.toString(sourcesId));
        System.out.println(roleId);
        //2、往中间表中插入权限及角色ID
        roleService.save(roleId,sourcesId);
        return "redirect:/role.jsp";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getList(){
        return roleService.getList();
    }
}
