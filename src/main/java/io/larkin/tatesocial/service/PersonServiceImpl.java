package io.larkin.tatesocial.service;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.larkin.tatesocial.model.Artwork;
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

	public void contributeToArtwork(Person p, Artwork a) {
		p.contributedTo(a);
		Transaction tx = graphDatabase.beginTx();
		try {
        	repo.save(p);
        	tx.success();
        } finally {
        	tx.close();
        }
	}
}
