package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.larkin.tatesocial.model.Person;
import io.larkin.tatesocial.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository repo;
	
	@Override
	public Person getPerson(String name) {
		return repo.getPersonByName(name);
	}

}
