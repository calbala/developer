package com.cognizant.developer.challenge.eventsourcing.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceQuote {
	
	@Id
	private String id;
	private String customerID;
	private List<String> driverName;
	private List<String> vehicleName;
	private Long quoteValue;

}
