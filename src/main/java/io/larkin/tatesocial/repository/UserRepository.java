package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.service.SocialUserDetailsService;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, SocialUserDetailsService {
	User findByLogin(String login);
}
