package com.jetsen.client;

import com.jetsen.entity.Customer;
import com.jetsen.service.CustomerService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NICKEL
 */
@RestController
@RequestMapping("/dubbo")
public class DubboClient {
    @Reference(lazy = true)
    private CustomerService customerService;

    @RequestMapping("/test")
    public List<Customer> getCutomers() {
        return customerService.selectAllCustomer();
    }
}
