package com.offcn.cust.service.impl;

import com.offcn.cust.service.CustomerService;
import com.offcn.cust.bean.Customer;
import com.offcn.cust.bean.CustomerExample;
import com.offcn.cust.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Transactional
    public List<Customer> customerList() {
        CustomerExample customerExample = new CustomerExample();
        List<Customer> list = customerMapper.selectByExample(customerExample);
        return list;
    }
    @Transactional
    public void addcustomer(Customer customer) {
        customerMapper.insert(customer);
    }

    public boolean validateComname(String comname) {
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();
        criteria.andComnameEqualTo(comname);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        if (customers.size() == 0){
            return true;
        } else {
            return  false;
        }
    }
    @Transactional(rollbackFor = SQLException.class)
    public boolean deleteCustByIds(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int result = customerMapper.deleteByExample(example);
        if (result > 0){
            return true;
        } else {
            return false;
        }
    }

    public Customer getCustmerById(String id) {
        Customer customer = customerMapper.selectByPrimaryKey(Integer.parseInt(id));
        return customer;
    }
    @Transactional
    public void updateById(Customer customer) {
        CustomerExample customerExample = new CustomerExample();
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();
        if (cid == 0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1 = customerExample.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            customerExample.or(criteria1);
        } else if (cid == 1){
            criteria.andComnameLike("%"+keyword+"%");
        } else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }

        if (orderby == 1){
            customerExample.setOrderByClause("id desc");
        }

        List<Customer> list = customerMapper.selectByExample(customerExample);
        return list;
    }
}
