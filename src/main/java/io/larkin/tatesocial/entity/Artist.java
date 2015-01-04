package io.larkin.tatesocial.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

public class Artist extends Person {

	public Artist() {
		super();
	}
	
	public Artist(String name) {
		super(name);
	}
	
	@RelatedTo(type = "CONTRIBUTED_TO", direction=Direction.OUTGOING)
	private @Fetch Set<Artwork> artworks;
	
	public Set<Artwork> getArtworks() {
		return artworks;
	}

	public void contributedTo(Artwork artwork) {
		if (artworks == null) {
			artworks = new HashSet<Artwork>();
		}
		artworks.add(artwork);
	}
	
	@RelatedTo(type = "PART_OF", direction=Direction.OUTGOING)
	private @Fetch Set<Movement> movements;
	
	public Set<Movement> getMovements() {
		return movements;
	}
}
