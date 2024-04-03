package com.ian.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ian.springboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}