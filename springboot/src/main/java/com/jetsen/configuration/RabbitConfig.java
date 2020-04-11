package com.jetsen.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NICKEL
 */
//@Configuration
@Slf4j
public class RabbitConfig {
    @Value("${spring.rabbitmq.queue1}")
    public String queue1;
    @Value("${spring.rabbitmq.queue2}")
    public String queue2;
    @Value("${spring.rabbitmq.exchange}")
    public String exchange;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("ConfirmCallback:     " + "相关数据：" + correlationData);
            log.info("ConfirmCallback:     " + "确认情况：" + ack);
            log.info("ConfirmCallback:     " + "原因：" + cause);
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback:     " + "消息：" + message);
            log.info("ReturnCallback:     " + "回应码：" + replyCode);
            log.info("ReturnCallback:     " + "回应信息：" + replyText);
            log.info("ReturnCallback:     " + "交换机：" + exchange);
            log.info("ReturnCallback:     " + "路由键：" + routingKey);
        });

        return rabbitTemplate;
    }

    @Bean
    public Queue firstQueue() {
        return new Queue(queue1);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(queue2);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(queue1);
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
