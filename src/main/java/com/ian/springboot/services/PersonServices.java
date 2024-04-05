package com.ian.springboot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ian.springboot.controllers.PersonController;
import com.ian.springboot.data.vo.v1.PersonVo;
import com.ian.springboot.exceptions.RequiredObjectIsNullException;
import com.ian.springboot.exceptions.ResourceNotFoundException;
import com.ian.springboot.mapper.DozerMapper;
import com.ian.springboot.model.Person;
import com.ian.springboot.repositories.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private PersonRepository repository;

	public List<PersonVo> findAll() {
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVo.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return persons;
	}

	public PersonVo findById(Long id) {
		logger.info("Finding one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVo.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public PersonVo create(PersonVo person) {
		logger.info("CREATING PERSON");
		
		if(person == null) {
			throw new RequiredObjectIsNullException();
		}
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(entity, PersonVo.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public PersonVo update(PersonVo person) {
		logger.info("UPDATING PERSON");

		if(person == null) {
			throw new RequiredObjectIsNullException();
		}
		
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(entity, PersonVo.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public void delete(Long id) {
		logger.info("DELETING PERSON");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}