package com.ian.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.ian.springboot.model.Person;

@Service
public class PersonServices {
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		return persons;
	}

	public Person findById(String id) {
		logger.info("Finding one person!");

		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Ian");
		person.setLastName("Soares");
		person.setAddress("Fortaleza - Ceará - Brasil");
		person.setGender("Male");
		return person;
	}
	
	public Person create(Person person) {
		logger.info("CREATING PERSON");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("UPDATING PERSON");
		return person;
	}
	
	public void delete(String id) {
		logger.info("DELETING PERSON");
	}
}