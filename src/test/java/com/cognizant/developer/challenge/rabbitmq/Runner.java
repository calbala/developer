package com.cognizant.developer.challenge.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cognizant.developer.challenge.eventsourcing.components.EventsConsumer;
import com.cognizant.developer.challenge.eventsourcing.config.RabbitMQConfig;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final EventsConsumer consumer;

    public Runner(EventsConsumer consumer, RabbitTemplate rabbitTemplate) {
        this.consumer = consumer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}