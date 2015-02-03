package io.larkin.tatesocial.repository;

import io.larkin.tatesocial.entity.User;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

public interface UserRepository extends GraphRepository<User>, RelationshipOperationsRepository<User>, SocialUserDetailsService {
	User findByLogin(String login);
}
