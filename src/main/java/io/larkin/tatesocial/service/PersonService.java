package io.larkin.tatesocial.service;

import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.model.Person;

public interface PersonService {
	
	Person getPersonByName(String name);
	
	void savePerson(Person p);
	
	void contributeToArtwork(Person p, Artwork a);
}
