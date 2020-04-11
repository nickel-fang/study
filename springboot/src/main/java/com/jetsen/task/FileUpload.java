package com.jetsen.task;

import com.jetsen.entity.Quote;
import com.jetsen.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;

/**
 * @author NICKEL
 */
@Configuration
public class FileUpload {
    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulWebservice.class);

    @Autowired
    private StorageService storageService;

    @Bean(value = "commandLineRunnerOfFileUpload")
    @Order(value = 3)
    public CommandLineRunner run(StorageService storageService) throws Exception {
        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    //@PostConstruct
    @PreDestroy
    public void destory() throws Exception {
        log.info("destory the application!");
        storageService.deleteAll();
    }
}
