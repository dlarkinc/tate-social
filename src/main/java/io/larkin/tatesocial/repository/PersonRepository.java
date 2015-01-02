package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.model.Person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository  extends CrudRepository<Person, String> {
	
	Person findById(Long id);
	
	Person findByName(String name);
	
	Iterable<Artwork> findByArtworksTitle(String title);
}
