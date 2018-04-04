package com.cognizant.developer.challenge.eventsourcing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.developer.challenge.eventsourcing.components.EventsPublisher;
import com.cognizant.developer.challenge.eventsourcing.model.Customer;


@RestController
@RequestMapping("/customer")
public class CustomerManagementController {
	
	@Autowired
	private EventsPublisher epub;
	
	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public Customer addCustomer(Customer customer) {
		return epub.publishCustomer(customer);
        
    }
	

}
