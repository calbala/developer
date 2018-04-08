package com.cognizant.developer.challenge.eventsourcing.repository;

import com.cognizant.developer.challenge.eventsourcing.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
