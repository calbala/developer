package com.cognizant.developer.challenge.eventsourcing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String zip;
	
		
	
}
