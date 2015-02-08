package io.larkin.tatesocial.controller;

import io.larkin.tatesocial.entity.Gallery;
import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.service.GalleryService;
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

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GalleryService galleryService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		int pageNum = 0;
		int pageSize = 10;

		User user = userService.getUserFromSession();
		Page<Gallery> galleries = galleryService.getGalleryPageForUser(user, new PageRequest(pageNum, pageSize));
		
		model.addAttribute("galleries", galleries);
		
		return "gallery/index";
	}
	
	@RequestMapping("/{nodeId}")
	public String view(@PathVariable long nodeId, Model model) {
		Gallery gallery = galleryService.getGalleryById(nodeId);
//		User user = userService.getUserFromSession();
//		boolean appreciates = false;
//		if (gallery.getUsers().contains(user)) {
//			appreciates = true;
//		}
//		model.addAttribute("appreciates", appreciates);
		model.addAttribute("gallery", gallery);
		return "gallery/view";
	}

	@RequestMapping("/page/{pageNum}")
	public String indexPaged(@PathVariable int pageNum, Model model) {
		
		// TODO: parameterise all the page sizes throughout
		int pageSize = 10;
		
		Sort sort = new Sort(Direction.ASC, "artist.name");
		User user = userService.getUserFromSession();
		
		Page<Gallery> galleries = galleryService.getGalleryPageForUser(user, new PageRequest(pageNum-1, pageSize, sort));
		
		model.addAttribute("galleries", galleries);
		
		return "gallery/index";
	}
	
//	@RequestMapping("/{nodeId}/appreciate/toggle")
//	public String appreciate(@PathVariable long nodeId, Model model, RedirectAttributes redir) {
//
//		// TODO: Convert this to an ajax handler method
//		
//		Artist artist = artistService.getArtistById(nodeId);
//		User user = userService.getUserFromSession();
//		boolean appreciate = false;
//
//		if (!artist.getUsers().contains(user)) {
//			appreciate = true;
//		}
//		user.appreciates(artist, appreciate);
//		userService.saveUser(user);
//
//		redir.addFlashAttribute("appreciates", appreciate);
//		redir.addFlashAttribute("artist", artist);
//		return "redirect:/artist/" + nodeId;
//	}
}
