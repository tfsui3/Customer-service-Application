package com.zycode.demo.customer;

import com.zycode.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
//        throw new ApiRequestException("Oops cannot get all customers");
        return customerService.getAllCustomers();
    }

    @PostMapping
    public void addNewCustomer(@RequestBody @Valid Customer customer){

        customerService.addNewCustomer(customer);
    }
}