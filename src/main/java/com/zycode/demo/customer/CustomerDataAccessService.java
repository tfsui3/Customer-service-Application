package com.zycode.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomerDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> selectAllCustomers(){
        String sql = "" +
                "SELECT " +
                " customer_id," +
                " first_name,"+
                " last_name,"+
                " email,"+
                " gender "+
                "From customer";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            String customerIdStr = resultSet.getString("customer_id");
            UUID customerId = UUID.fromString(customerIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            String genderStr = resultSet.getString("gender").toUpperCase();
            Customer.Gender gender = Customer.Gender.valueOf(genderStr);
            return new Customer(
                    customerId, firstName, lastName, email, gender
            );
        });
    }
}
