package com.zycode.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDataAccessService customerDataAccessService;

    @Autowired
    public CustomerService(CustomerDataAccessService customerDataAccessService){
        this.customerDataAccessService = customerDataAccessService;
    }
    public List<Customer> getAllCustomers(){
        return customerDataAccessService.selectAllCustomers();
    }
}
