package com.jetsen.task;

import com.jetsen.entity.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * @author NICKEL
 */
//@Configuration
public class ConsumingRestfulWebservice {
    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulWebservice.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean(value = "commandLineRunnerOfConsumingRestfulWebservice")
    @Order(value = 1)
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            log.info("execute consuming restful web service");
            Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }
}
