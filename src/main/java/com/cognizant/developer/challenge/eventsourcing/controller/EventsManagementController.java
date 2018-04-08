package com.cognizant.developer.challenge.eventsourcing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.developer.challenge.eventsourcing.repository.EventRepository;



@RestController
@RequestMapping("/events")
public class EventsManagementController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
    public String listAll() {
		return eventRepository.listAllEvents();
        
    }
	
	
	

}
