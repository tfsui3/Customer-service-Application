package com.zycode.demo.customer;

import com.zycode.demo.EmailValidator;
import com.zycode.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerDataAccessService customerDataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public CustomerService(CustomerDataAccessService customerDataAccessService,
                           EmailValidator emailValidator){
        this.customerDataAccessService = customerDataAccessService;
        this.emailValidator = emailValidator;
    }
    public List<Customer> getAllCustomers(){
        return customerDataAccessService.selectAllCustomers();
    }

    public void addNewCustomer(Customer customer) {
        addNewCustomer(null, customer);
    }
    public void addNewCustomer(UUID customerId, Customer customer) {
        UUID newCustomerId = Optional.ofNullable(customerId).
                orElse(UUID.randomUUID());

        if (!emailValidator.test(customer.getEmail())){
            throw new ApiRequestException(customer.getEmail() + " is not valid");
        }
        //TODO: Verify that email is not taken
        if(customerDataAccessService.isEmailTaken(customer.getEmail())){
            throw new ApiRequestException(customer.getEmail() + " is taken");
        }

        customerDataAccessService.insertCustomer(newCustomerId,customer);
    }
}
