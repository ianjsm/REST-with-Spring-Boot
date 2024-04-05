package com.ian.springboot.unitTests.mocks;

import java.util.ArrayList;
import java.util.List;

import com.ian.springboot.data.vo.v1.PersonVo;
import com.ian.springboot.model.Person;

public class Mock {

	public Person mockEntity() {
		return mockEntity(0);
	}

	public PersonVo mockVo() {
		return mockVo(0);
	}

	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}

	public List<PersonVo> mockVoList() {
		List<PersonVo> persons = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockVo(i));
		}
		return persons;
	}

	public Person mockEntity(Integer number) {
		Person person = new Person();
		person.setAddress("Addres Test" + number);
		person.setFirstName("First Name Test" + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Female");
		person.setId(number.longValue());
		person.setLastName("Last Name Test" + number);
		return person;
	}

	public PersonVo mockVo(Integer number) {
		PersonVo person = new PersonVo();
		person.setAddress("Addres Test" + number);
		person.setFirstName("First Name Test" + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Female");
		person.setKey(number.longValue());
		person.setLastName("Last Name Test" + number);
		return person;
	}
}