package com.ian.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ian.springboot.model.Person;
import com.ian.springboot.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping("/{id}")
	public Person findById(@PathVariable(value = "id") String id) throws Exception {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Person> findAll() throws Exception {
		return service.findAll();
	}
	
	@PostMapping
	public Person create(@RequestBody Person person){
		return service.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person){
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") String id){
		service.delete(id);
	}
}