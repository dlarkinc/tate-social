package io.larkin.tatesocial.service;

import io.larkin.tatesocial.entity.User;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface SocialUserDetailsService extends UserDetailsService {
    @Override
    SocialUserDetails loadUserByUsername(String login)
                 throws UsernameNotFoundException, DataAccessException;

    User getUserFromSession();

    @Transactional
    User register(String login, String name, String password);

    @Transactional
    void addFriend(String login, final User userFromSession);
}
