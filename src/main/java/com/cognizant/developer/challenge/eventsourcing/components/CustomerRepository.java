package com.cognizant.developer.challenge.eventsourcing.components;

import com.cognizant.developer.challenge.eventsourcing.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
