package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.larkin.tatesocial.entity.Person;
import io.larkin.tatesocial.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository repo;
	
	@Override
	public Person getPersonByName(String name) {
		return repo.findByName(name);
	}
	
	public void savePerson(Person p) {
       	repo.save(p);
	}

	@Override
	public Page<Person> getPersonPage(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
