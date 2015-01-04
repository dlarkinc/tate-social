package io.larkin.tatesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.larkin.tatesocial.entity.Person;

public interface PersonService {
	
	Person getPersonByName(String name);
	
	void savePerson(Person p);
	
	Page<Person> getPersonPage(Pageable pageable);
}
