package com.jetsen.service.impl;

import com.jetsen.entity.Customer;
import com.jetsen.mapper.CustomerMapper;
import com.jetsen.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author NICKEL
 */
@Service
@org.apache.dubbo.config.annotation.Service
@Slf4j
@CacheConfig(cacheNames = "customer")
public class CustomerServiceImpl implements CustomerService, EchoService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void insertCustomer(Customer customer) {
        customerMapper.insert(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertCustomer(List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
//            if (i == 1) {
//                throw new RuntimeException("执行出错！");
//            }
            insertCustomer(customers.get(i));
            log.info("customer插入成功：" + customers.get(i).getId());
        }
    }

    @Override
    @Cacheable(cacheNames = "customer", key = "#root.methodName+#id", unless = "#result==null")
    public Customer selectByCustomerId(long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Customer> selectAllCustomer() {
        return customerMapper.selectAllCustomer();
    }

    @Override
    public Object $echo(Object o) {
        return "OK";
    }
}
