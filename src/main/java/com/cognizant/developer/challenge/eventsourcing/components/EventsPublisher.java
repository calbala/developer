package com.cognizant.developer.challenge.eventsourcing.components;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.developer.challenge.eventsourcing.config.RabbitMQConfig;
import com.cognizant.developer.challenge.eventsourcing.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventsPublisher {

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    private EventsConsumer consumer;

	public Customer publishCustomer(Customer customer) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, mapper.writeValueAsString(customer));
	        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | AmqpException | JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return customer;//repository.save(customer);
		
	}
}
