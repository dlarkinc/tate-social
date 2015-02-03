package io.larkin.tatesocial.controller;
 
import io.larkin.tatesocial.entity.Artist;
import io.larkin.tatesocial.service.ArtistService;

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
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		Page<Artist> artists = artistService.getArtistPage(new PageRequest(pageNum, pageSize));
		
		model.addAttribute("artists", artists);
		
		return "artist/index";
	}
	
	@RequestMapping("/{NodeId}")
	public String view(@PathVariable Long nodeId, Model model) {
		Artist artist = artistService.getArtistById(nodeId);
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
	
	@RequestMapping("/{nodeId}/appreciate/{toggle}")
	public void appreciate(@PathVariable int nodeId, @PathVariable boolean toggle, Model model) {
		
	}
}
