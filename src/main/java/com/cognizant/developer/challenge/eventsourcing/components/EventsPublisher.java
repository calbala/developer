package com.cognizant.developer.challenge.eventsourcing.components;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.developer.challenge.eventsourcing.config.RabbitMQConfig;
import com.cognizant.developer.challenge.eventsourcing.model.Customer;
import com.cognizant.developer.challenge.eventsourcing.model.CustomerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventsPublisher {

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    private EventsConsumer consumer;

	public Customer addCustomer(Customer customer) {
		CustomerEvent event = new CustomerEvent();
		event.setCustomer(customer);
		event.setEventType("add-customer");
		event.setEventTime(new Date());
		ObjectMapper mapper = new ObjectMapper();
		try {
			rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, mapper.writeValueAsString(event));
	        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | AmqpException | JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return customer;//repository.save(customer);
		
	}
	
	public Customer deleteCustomer(Customer customer) {
		CustomerEvent event = new CustomerEvent();
		event.setCustomer(customer);
		event.setEventType("delete-customer");
		event.setEventTime(new Date());
		ObjectMapper mapper = new ObjectMapper();
		try {
			rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, mapper.writeValueAsString(event));
	        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | AmqpException | JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return customer;//repository.save(customer);
		
	}
}
