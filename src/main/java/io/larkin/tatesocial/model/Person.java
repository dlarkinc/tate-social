package io.larkin.tatesocial.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

	@GraphId Long id;
	
	public String name;
	
	public Person() {}
    public Person(String name) { this.name = name; }

	@RelatedTo(type = "CONTRIBUTED_TO", direction=Direction.OUTGOING)
    public @Fetch Set<Artwork> artworks;
	
	public void contributedTo(Artwork artwork) {
		if (artworks == null) {
			artworks = new HashSet<Artwork>();
		}
		artworks.add(artwork);
	}

}
