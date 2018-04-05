package com.cognizant.developer.challenge.rabbitmq;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.developer.challenge.eventsourcing.Application;
import com.cognizant.developer.challenge.eventsourcing.components.EventsConsumer;
import com.cognizant.developer.challenge.eventsourcing.config.RabbitMQConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitTemplateTest {

	@MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EventsConsumer consumer;

    @Test
    public void test() throws Exception {
        rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Hello from RabbitMQ!");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
