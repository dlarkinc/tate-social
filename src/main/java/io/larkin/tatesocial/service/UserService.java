package io.larkin.tatesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.larkin.tatesocial.entity.User;

public interface UserService extends SocialUserDetailsService{
	
	User getUserByLogin(String login);
	
	void saveUser(User user);
	
	Page<User> getUserPage(Pageable pageable);
}
