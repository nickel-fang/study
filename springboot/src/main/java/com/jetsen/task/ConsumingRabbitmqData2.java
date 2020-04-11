package com.jetsen.task;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NICKEL
 */
@Component
@Slf4j
//@RabbitListener(queues = {"topic.woman"})
public class ConsumingRabbitmqData2 {
    @RabbitHandler
    public void processMessage(Map message) {
        log.info("消费rabbitmq的消息：" + message.toString());
    }

}
