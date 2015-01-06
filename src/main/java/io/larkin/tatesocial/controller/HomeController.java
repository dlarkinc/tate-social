package io.larkin.tatesocial.controller;

import io.larkin.tatesocial.entity.Artist;
import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.repository.ArtworkRepository;
import io.larkin.tatesocial.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	ArtworkRepository artworkRepository;
	
	@RequestMapping("/artist/name/{name}")
	public String index(@PathVariable String name, Model model) {
		model.addAttribute("message", "Welcome to the Tate social app.");
		
		Artist bob = artistService.getArtistByName(name);
		model.addAttribute("person", bob);
	
		model.addAttribute("artworks", bob.getArtworks());
		
		return "home";
	}
	
	@RequestMapping("/contribute")
	public String contribute(@RequestParam String title, Model model) {
		
		Artist artist = artistService.getArtistByName("Smith, Bob");
		
		if (artist == null) {
			artist = new Artist("Smith, Bob");
		}
		
		Artwork artwork = new Artwork(title);
		
		artistService.contributeToArtwork(artist, artwork);

		model.addAttribute("node", 1);
		
		return "addTest";
	}

	@RequestMapping("/artist/add/{name}")
	public String add(@PathVariable String name, Model model) {
		
		if (name != null && name.length() > 0) {
			artistService.saveArtist(new Artist(name));
			model.addAttribute("name", name);
		} else {
			model.addAttribute("name", "[no name]");
		}
		
		return "artist/add";
	}
}
