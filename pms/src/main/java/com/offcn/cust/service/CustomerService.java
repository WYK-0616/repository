package com.offcn.cust.service;
import com.offcn.cust.bean.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> customerList();

    void addcustomer(Customer customer);

    boolean validateComname(String comname);

    boolean deleteCustByIds(Integer[] ids);

    Customer getCustmerById(String id);

    void updateById(Customer customer);

    List<Customer> search(Integer cid, String keyword, Integer orderby);
}
