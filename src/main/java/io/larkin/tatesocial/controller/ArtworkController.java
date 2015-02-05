package io.larkin.tatesocial.controller;
 
import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.repository.UserRepository;
import io.larkin.tatesocial.service.ArtworkService;

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
@RequestMapping("/artwork")
public class ArtworkController {

	@Autowired
	ArtworkService artworkService;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		int pageNum = 0;
		int pageSize = 10; 
		
		Page<Artwork> artworks = artworkService.getArtworkPage(new PageRequest(pageNum, pageSize));
		
		model.addAttribute("artworks", artworks);
		
		return "artwork/index";
	}
	
	@RequestMapping("/{nodeId}")
	public String view(@PathVariable long nodeId, Model model) {
		Artwork artwork = artworkService.getArtworkById(nodeId);
		User user = userRepository.getUserFromSession();
		boolean appreciates = false;
		if (artwork.getUsers().contains(user)) {
			appreciates = true;
		}
		model.addAttribute("appreciates", appreciates);
		model.addAttribute("artwork", artwork);
		return "artwork/view";
	}

	@RequestMapping("/page/{pageNum}")
	public String indexPaged(@PathVariable int pageNum, Model model) {
		
		int pageSize = 10;
		
		Sort sort = new Sort(Direction.ASC, "artwork.title");
		
		Page<Artwork> artworks = artworkService.getArtworkPage(new PageRequest(pageNum-1, pageSize, sort));
		
		model.addAttribute("artworks", artworks);
		
		return "artwork/index";
	}
	
	@RequestMapping("/{nodeId}/appreciate/toggle")
	public String appreciate(@PathVariable long nodeId, Model model, RedirectAttributes redir) {

		// TODO: Convert this to an ajax handler method
		
		Artwork artwork = artworkService.getArtworkById(nodeId);
		User user = userRepository.getUserFromSession();
		boolean appreciate = false;

		if (!artwork.getUsers().contains(user)) {
			appreciate = true;
		}
		user.appreciates(artwork, appreciate);
		userRepository.save(user);

		redir.addFlashAttribute("appreciates", appreciate);
		redir.addFlashAttribute("artwork", artwork);
		return "redirect:/artwork/" + nodeId;
	}
}
