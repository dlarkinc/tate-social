package io.larkin.tatesocial.controller;

import java.util.Set;

import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.model.Person;
import io.larkin.tatesocial.repository.ArtworkRepository;
import io.larkin.tatesocial.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ArtworkRepository artworkRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Welcome to the Tate social app.");
		//Person person = personRepository.getPersonByName("Soest, Gilbert");
		Person person = personRepository.getPersonById(new Long(14));
		model.addAttribute("person", person);
		
		Artwork artwork = artworkRepository.getArtworkById(new Long(32187));
		model.addAttribute("artwork", artwork);
		
//		
//		Iterable<Artwork> artworks = artworkRepository.getArtworksByPerson(person);
//		//Set<Artwork> artworks = person.getArtworks();
//		
//		int count = 0;
//		for (Artwork a : artworks) {
//			count++;
//			String s = a.getTitle();
//			Long i = a.getId();
//		}
//		
//		model.addAttribute("artworks", artworks);
//		
		return "home";
	}
	
	@RequestMapping("/addTest")
	public String addTest(Model model) {
		
		
		
		return "addTest";
	}
}
