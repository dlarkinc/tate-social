package io.larkin.tatesocial.controller;

import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.model.Person;
import io.larkin.tatesocial.repository.ArtworkRepository;
import io.larkin.tatesocial.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ArtworkRepository artworkRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Welcome to the Tate social app.");
		
		Person bob = personService.getPersonByName("Smith, Bob");
		model.addAttribute("person", bob);
	
		model.addAttribute("artworks", bob.artworks);
		
		return "home";
	}
	
	@RequestMapping("/contribute")
	public String contribute(@RequestParam String title, Model model) {
		
		Person p = personService.getPersonByName("Smith, Bob");
		
		Artwork a = new Artwork(title);
		
		personService.contributeToArtwork(p, a);

		model.addAttribute("node", 1);
		
		return "addTest";
	}
}
