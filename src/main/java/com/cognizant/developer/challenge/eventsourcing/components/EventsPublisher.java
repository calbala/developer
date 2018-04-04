package com.cognizant.developer.challenge.eventsourcing.components;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.developer.challenge.eventsourcing.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventsPublisher {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public Customer publishCustomer(Customer customer) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			this.rabbitTemplate.convertAndSend("incoming-events", mapper.writeValueAsString(customer));
		} catch (AmqpException | JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;//repository.save(customer);
		
	}
}
