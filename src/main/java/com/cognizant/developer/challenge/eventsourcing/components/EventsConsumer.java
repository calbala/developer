package com.cognizant.developer.challenge.eventsourcing.components;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.developer.challenge.eventsourcing.model.CustomerEvent;
import com.cognizant.developer.challenge.eventsourcing.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventsConsumer {
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Autowired
	private EventRepository eventRepository;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
        boolean persisted = eventRepository.save(message);
        if (persisted) {
        	// process the event
        	
        }else {
        	System.out.println("Failed: Event couldn't be persisted. " + message);
        } 
		
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
