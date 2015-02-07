package io.larkin.tatesocial.controller;

import io.larkin.tatesocial.entity.Artist;
import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.repository.UserRepository;
import io.larkin.tatesocial.service.ArtistService;
import io.larkin.tatesocial.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
		
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		Page<User> users = userService.getUserPage(new PageRequest(pageNum, pageSize));
		
		model.addAttribute("users", users);
		
		return "user/index";
	}
	
	@RequestMapping("/{login}")
	public String view(@PathVariable String login, Model model) {
		// TODO: Handle "pending" friend requests
		User user = userService.getUserByLogin(login);
		User me = userService.getUserFromSession();
		boolean isFriend = false;
		if (user.getFriends().contains(me)) {
			isFriend = true;
		}
		model.addAttribute("isFriend", isFriend);
		model.addAttribute("user", user);
		return "user/view";
	}

	@RequestMapping("/page/{pageNum}")
	public String indexPaged(@PathVariable int pageNum, Model model) {
		
		int pageSize = 10;
		
		Sort sort = new Sort(Direction.ASC, "user.name");
		
		Page<User> users = userService.getUserPage(new PageRequest(pageNum-1, pageSize, sort));
		
		model.addAttribute("users", users);
		
		return "user/index";
	}
	
	@RequestMapping("/{login}/befriend/toggle")
	public String befriend(@PathVariable String login, Model model, RedirectAttributes redir) {

		// TODO: Convert this to an ajax handler method
		// TODO: Handle "pending" friend requests
		
		User user = userService.getUserByLogin(login);
		User me = userService.getUserFromSession();
		boolean isFriend = false;

		if (!user.equals(me) && !user.getFriends().contains(user)) {
			isFriend = true;
		}
		user.befriend(user, isFriend);
		userService.saveUser(user);

		redir.addFlashAttribute("isFriend", isFriend);
		redir.addFlashAttribute("user", user);
		return "redirect:/user/" + login;
	}
}
