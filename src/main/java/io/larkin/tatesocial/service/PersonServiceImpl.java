package io.larkin.tatesocial.service;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.larkin.tatesocial.model.Person;
import io.larkin.tatesocial.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository repo;
	
	@Autowired
    GraphDatabase graphDatabase;
	
	@Override
	public Person getPersonByName(String name) {
		return repo.findByName(name);
	}
	
	public void savePerson(Person p) {
		Transaction tx = graphDatabase.beginTx();
        try {
        	repo.save(p);
        	tx.success();
        } finally {
        	tx.close();
        }
	}

	@Override
	public Page<Person> getPersonPage(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
