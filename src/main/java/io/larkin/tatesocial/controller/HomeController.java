package io.larkin.tatesocial.controller;

import io.larkin.tatesocial.model.Artist;
import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.model.Person;
import io.larkin.tatesocial.repository.ArtworkRepository;
import io.larkin.tatesocial.service.ArtistService;
import io.larkin.tatesocial.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	ArtworkRepository artworkRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Welcome to the Tate social app.");
		
		Artist bob = artistService.getArtistByName("Smith, Bob");
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
}
