package com.jetsen.task;

import com.jetsen.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NICKEL
 */
//@Configuration
public class ConsumingRelationalData {
    private static final Logger log = LoggerFactory.getLogger(ConsumingRelationalData.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean(value = "commandLineRunnerOfConsumingRelationalData")
    @Order(value = 2)
    public CommandLineRunner run() throws Exception {
        return args -> {
            log.info("execute consuming relational data");
            log.info("create tables");
            jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

            List<Object[]> splitUpNames = Arrays.asList("John Woo", "Nickel Fang", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                    .map(name -> name.split(" "))
                    .collect(Collectors.toList());
            splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

            jdbcTemplate.batchUpdate("insert into customers(first_name,last_name) values(?,?)", splitUpNames);

            log.info("Querying for customer records where first_name = 'Nickel':");
            jdbcTemplate.query("select id,first_name,last_name from customers where first_name=?", new Object[]{"Nickel"}, (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("memo")))
                    .forEach(customer -> log.info(customer.toString()));

        };
    }
}
