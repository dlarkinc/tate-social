package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.model.Artist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository  extends PagingAndSortingRepository<Artist, String> {
	
	Artist findById(Long id);
	
	Artist findByName(String name);
	
	@Query(value="MATCH (artist:Artist) RETURN artist", countQuery="MATCH (artist:Artist) return count(artist)")
	Page<Artist> findAll(Pageable page);
}
