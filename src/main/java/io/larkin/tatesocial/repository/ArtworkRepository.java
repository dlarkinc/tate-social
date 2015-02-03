package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.entity.Person;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArtworkRepository extends GraphRepository<Artwork>{
	
	Artwork getArtworkByTitle(String title);
	
	Artwork getArtworkById(Long id);
	
	@Query("START person=node({0}) MATCH person-[:CONTRIBUTED_TO]->a return a limit 20")
	Iterable<Artwork> getArtworksByPerson(Person person);
}
