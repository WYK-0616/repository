package com.offcn.emp.controller;

import com.offcn.emp.bean.Employee;
import com.offcn.emp.service.EmployeeService;
import com.offcn.system.bean.Sources;
import com.offcn.system.service.SourceService;
import com.offcn.utils.ImageVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SourceService sourceService;

    @RequestMapping(value = "/getManagerList",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagerList(){
        List<Employee> list = employeeService.getManagerList();
        return list;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response){
        try{
            ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
            BufferedImage image = ivc.getImage();  //获取验证码
            request.getSession().setAttribute("text", ivc.getText()); //将验证码的文本存在session中
            ivc.output(image, response.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/loginValidate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> loginValidate(String username, String password, String code, HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        String text = (String)session.getAttribute("text");
        if (text.equalsIgnoreCase(code)){
            Employee employee = employeeService.loginValidate(username,password);
            if (employee == null){
                map.put("statusCode",500);
            } else {
                //根据员工ID查询权限信息,获取资源列表
                List<Sources> sources = sourceService.getSourcesByEid(employee.getEid());
                session.setAttribute("sources",sources);
                session.setAttribute("loginUser",employee);
                map.put("statusCode",200);
            }
        } else {
            map.put("statusCode",499);
        }
        return map;
    }

    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addEmp(Employee employee,Integer[] rids){
        Map<String,Object> map = new HashMap<>();
        Integer eid = employeeService.addEmp(employee);
        boolean flag = employeeService.addRole(eid,rids);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",499);
        }
        return map;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try{
            session.removeAttribute("loginUser");
            map.put("statusCode",200);
        }catch (Exception ex){
            ex.printStackTrace();
            map.put("statusCode",499);
        }
        return map;
    }
}
