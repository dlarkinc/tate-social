package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.model.Person;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface PersonRepository  extends GraphRepository<Person>{
	Person getPersonById(Long id);
	Person getPersonByName(String name);
}
