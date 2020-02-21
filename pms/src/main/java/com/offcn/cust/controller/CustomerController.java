package com.offcn.cust.controller;

import com.offcn.cust.bean.Customer;
import com.offcn.cust.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //查询客户列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView customerList(){
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> list = customerService.customerList();
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getList(){
        List<Customer> list = customerService.customerList();
        return list;
    }
    //添加新客户
    @RequestMapping(value = "/addcustomer",method = RequestMethod.POST)
    public String addcustomer(Customer customer){
        customer.setAddtime(new Date());
        customerService.addcustomer(customer);
        return "redirect:/cust/list";
    }

    //客户公司名唯一验证
    @RequestMapping(value = "/validateComname",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> validateComname(String comname){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean result = customerService.validateComname(comname);
        if (result){
            map.put("result",200);
        }else {
            map.put("result",999);
        }
        return map;
    }

    //删除项目
    @RequestMapping(value = "/deleteCust",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteCustByIds(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            boolean flag = customerService.deleteCustByIds(ids);
            if (flag){
                map.put("statuCode",200);
            }
        } catch (Exception ex){
            map.put("statuCode",599);
            ex.printStackTrace();
        }
        return map;
    }

    //根据ID查询客户
    @RequestMapping(value = "/getCustmerById/{id}",method = RequestMethod.GET)
    public ModelAndView getCustmerById(@PathVariable("id")String id){
        ModelAndView mv = new ModelAndView("customer-edit");
        Customer customer = customerService.getCustmerById(id);
        mv.addObject("customer",customer);
        return mv;
    }

    @RequestMapping(value = "/getCustmer/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustmer(@PathVariable("id")String id){
        Customer customer = customerService.getCustmerById(id);
        System.out.println(customer);
        return customer;
    }
    //根据ID修改客户
    @RequestMapping(value = "/updateById",method = RequestMethod.PUT)
    public String updateById(Customer customer){
        customerService.updateById(customer);
        return "redirect:/cust/list";
    }
    //根据ID显示客户详情
    @RequestMapping(value = "/selectCustmerById/{id}",method = RequestMethod.GET)
    public ModelAndView selectCustmerById(@PathVariable("id")String id){
        ModelAndView mv = new ModelAndView("customer-look");
        Customer customer = customerService.getCustmerById(id);
        mv.addObject("customer",customer);
        return mv;
    }

    //根据条件搜索客户
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(Integer cid,String keyword,Integer orderby,Map<String,Object> map){
        List<Customer> list = customerService.search(cid,keyword,orderby);
        System.out.println(list);
        map.put("list",list);
        return "customer";
    }

    @RequestMapping(value = "/getCuscompanyList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCuscompanyList(){
        List<Customer> list = customerService.customerList();
        return list;
    }

    @RequestMapping(value = "/getCompanyperson/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer getCompanyperson(@PathVariable("id") String id){
        Customer custmer = customerService.getCustmerById(id);
        return custmer;
    }
}
