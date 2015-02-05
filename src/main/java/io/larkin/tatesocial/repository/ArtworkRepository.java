package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.Artwork;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArtworkRepository extends GraphRepository<Artwork>{
	
	Artwork findByTitle(String title);
	
	Artwork findById(Long id);
	
	@Query(value="MATCH (artwork:Artwork) RETURN artwork", countQuery="MATCH (artwork:Artwork) return count(artwork)")
	Page<Artwork> findAll(Pageable page);
	
//	@Query("START person=node({0}) MATCH person-[:CONTRIBUTED_TO]->a return a limit 20")
//	Iterable<Artwork> getArtworksByPerson(Person person);
}
