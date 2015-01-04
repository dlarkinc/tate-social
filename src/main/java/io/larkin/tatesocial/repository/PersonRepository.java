package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.Person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository  extends PagingAndSortingRepository<Person, String> {
	
	Person findById(Long id);
	
	Person findByName(String name);
	
	@Query(value="MATCH (person:Person) RETURN person", countQuery="MATCH (person:Person) return count(person)")
	Page<Person> findAll(Pageable page);
}
