package com.ian.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ian.springboot.data.vo.v1.PersonVo;
import com.ian.springboot.exceptions.ResourceNotFoundException;
import com.ian.springboot.mapper.Mapper;
import com.ian.springboot.model.Person;
import com.ian.springboot.repositories.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private PersonRepository repository;

	public List<PersonVo> findAll() {
		return Mapper.parseListObjects(repository.findAll(), PersonVo.class);
	}

	public PersonVo findById(Long id) {
		logger.info("Finding one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return Mapper.parseObject(entity, PersonVo.class);
	}

	public PersonVo create(PersonVo person) {
		logger.info("CREATING PERSON");
		var entity = Mapper.parseObject(person, Person.class);
		var vo = Mapper.parseObject(repository.save(entity), PersonVo.class);
		return vo;
	}

	public PersonVo update(PersonVo person) {
		logger.info("UPDATING PERSON");
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = Mapper.parseObject(repository.save(entity), PersonVo.class);
		return vo;
	}

	public void delete(Long id) {
		logger.info("DELETING PERSON");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}