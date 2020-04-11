package com.jetsen.web;

import com.jetsen.entity.Customer;
import com.jetsen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NICKEL
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/findAll")
    public List<Customer> findAll() {
        return customerService.selectAllCustomer();
    }

    @RequestMapping("/batchInsert")
    public void batchInsert() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setFirstName("Nickel");
        customer1.setLastName("Fang");
        Customer customer2 = new Customer();
        customer2.setFirstName("Tiezhong");
        customer2.setLastName("Shang");
        customers.add(customer1);
        customers.add(customer2);
        customerService.batchInsertCustomer(customers);
    }

    @GetMapping("/getCustomer")
    public Customer getCustomerById(long id) {
        return customerService.selectByCustomerId(id);
    }
}
