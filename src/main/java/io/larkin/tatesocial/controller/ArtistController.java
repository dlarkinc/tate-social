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
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		Page<Artist> artists = artistService.getArtistPage(new PageRequest(pageNum, pageSize));
		
		model.addAttribute("artists", artists);
		
		return "artist/index";
	}
	
	@RequestMapping("/{nodeId}")
	public String view(@PathVariable long nodeId, Model model) {
		Artist artist = artistService.getArtistById(nodeId);
		User user = userService.getUserFromSession();
		boolean appreciates = false;
		if (artist.getUsers().contains(user)) {
			appreciates = true;
		}
		model.addAttribute("appreciates", appreciates);
		model.addAttribute("artist", artist);
		return "artist/view";
	}

	@RequestMapping("/page/{pageNum}")
	public String indexPaged(@PathVariable int pageNum, Model model) {
		
		int pageSize = 10;
		
		Sort sort = new Sort(Direction.ASC, "artist.name");
		
		Page<Artist> artists = artistService.getArtistPage(new PageRequest(pageNum-1, pageSize, sort));
		
		model.addAttribute("artists", artists);
		
		return "artist/index";
	}
	
	@RequestMapping("/{nodeId}/appreciate/toggle")
	public String appreciate(@PathVariable long nodeId, Model model, RedirectAttributes redir) {

		// TODO: Convert this to an ajax handler method
		
		Artist artist = artistService.getArtistById(nodeId);
		User user = userService.getUserFromSession();
		boolean appreciate = false;

		if (!artist.getUsers().contains(user)) {
			appreciate = true;
		}
		user.appreciates(artist, appreciate);
		userService.saveUser(user);

		redir.addFlashAttribute("appreciates", appreciate);
		redir.addFlashAttribute("artist", artist);
		return "redirect:/artist/" + nodeId;
	}
}
