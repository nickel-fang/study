package com.jetsen.service.impl;

import com.jetsen.service.CommonService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(serviceName = "CommonService", targetNamespace = "http://service.jetsen.com",
        endpointInterface = "com.jetsen.service.CommonService")
public class CommonServiceImpl implements CommonService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
