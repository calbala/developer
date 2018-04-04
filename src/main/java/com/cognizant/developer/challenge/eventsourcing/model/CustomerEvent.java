package com.cognizant.developer.challenge.eventsourcing.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {
	
	private Customer customer;
	private String   eventType;
	private Date     eventTime;

}
