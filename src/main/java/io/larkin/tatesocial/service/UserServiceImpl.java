package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	public UserServiceImpl() {
		super();
	}
	
	public void saveUser(User user) {
		repo.save(user);
	}

	public Page<User> getUserPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public User getUserByLogin(String login) {
		return repo.findByLogin(login);
	}

	@Override
	public SocialUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		final User user = repo.findByLogin(login);
		if (user == null)
			throw new UsernameNotFoundException("Username not found: " + login);
		return new SocialUserDetails(user);
	}

	@Override
	public User getUserFromSession() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof SocialUserDetails) {
			SocialUserDetails userDetails = (SocialUserDetails) principal;
			return userDetails.getUser();
		}
		return null;
	}

	@Override
	public User register(String login, String name, String password) {
	      User found = repo.findByLogin(login);
	        if (found!=null) throw new RuntimeException("Login already taken: "+login);
	        if (name==null || name.isEmpty()) throw new RuntimeException("No name provided.");
	        if (password==null || password.isEmpty()) throw new RuntimeException("No password provided.");
	        User user=repo.save(new User(login,name,password,User.Roles.ROLE_USER));
	        setUserInSession(user);
	        return user;
	}
	
   void setUserInSession(User user) {
        SecurityContext context = SecurityContextHolder.getContext();
        SocialUserDetails userDetails = new SocialUserDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(),userDetails.getAuthorities());
        context.setAuthentication(authentication);
    }

	@Override
	public void addFriend(String friendLogin, final User me) {
        User friend = repo.findByLogin(friendLogin);
        if (!me.equals(friend)) {
            me.befriend(friend, true);
            repo.save(me);
        }	
	}
}
