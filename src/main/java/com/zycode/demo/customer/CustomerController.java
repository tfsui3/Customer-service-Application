package com.zycode.demo.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public List<Customer> getAllStudent(){
        return List.of(
                new Customer(UUID.randomUUID(),"James","Bond","jamesbond@gamail.com", Customer.Gender.MALE),
                new Customer(UUID.randomUUID(),"Elisa","Tamara","elisatamara@gmail.com", Customer.Gender.FEMALE)
        );
    }
}
