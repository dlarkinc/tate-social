package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	User findByLogin(String login);
	
	@Query(value="MATCH (user:User) RETURN user", countQuery="MATCH (user:User) return count(user)")
	Page<User> findAll(Pageable page);
}
