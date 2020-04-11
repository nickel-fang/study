package com.jetsen.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author NICKEL
 */
@RestController
@RequestMapping("/rabbitmq")
public class rabbitmqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendTopicMessage")
    public String sendTopicMessage(String exchange, String routingKey) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: " + routingKey;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        rabbitTemplate.convertAndSend(exchange, routingKey, manMap);
        return "ok";
    }
}
