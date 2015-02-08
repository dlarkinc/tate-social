package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.Gallery;
import io.larkin.tatesocial.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface GalleryRepository  extends PagingAndSortingRepository<Gallery, String> {
	
	Gallery findById(Long id);
	
	@Query(value="MATCH (gallery:Gallery)<--(user:User {id:{user}.id}) RETURN gallery", countQuery="MATCH (gallery:Gallery)<--(user:User {id:{user}.id}) return count(gallery)")
	Page<Gallery> findByUser(@Param("user") User user, Pageable page);
}
