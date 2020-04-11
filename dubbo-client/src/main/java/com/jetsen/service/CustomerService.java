package com.jetsen.service;

import com.jetsen.entity.Customer;

import java.util.List;

/**
 * @author NICKEL
 */
public interface CustomerService {
    void insertCustomer(Customer customer);

    void batchInsertCustomer(List<Customer> customers);

    Customer selectByCustomerId(long id);

    List<Customer> selectAllCustomer();
}
