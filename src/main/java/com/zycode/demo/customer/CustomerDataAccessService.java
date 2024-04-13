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

    public int insertCustomer(UUID customerId, Customer customer) {
        String sql = "" +
                "INSERT INTO customer ("+
                "customer_id,"+
                "first_name,"+
                "last_name,"+
                "email,"+
                "gender) " +
                "VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                customerId,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getGender().name().toUpperCase()
        );
    }

    public List<Customer> selectAllCustomers(){
        String sql = "" +
                "SELECT " +
                "customer_id," +
                "first_name,"+
                "last_name,"+
                "email,"+
                "gender "+
                "FROM customer";
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

    @SuppressWarnings("ConstantConditions")
    public boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS(" +
                "    SELECT 1 " +
                "    FROM customer " +
                "    WHERE email = ?" +
                "  )";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {email},
                (resultSet, i) -> resultSet.getBoolean(1));

    }
}
