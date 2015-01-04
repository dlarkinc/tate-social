package io.larkin.tatesocial.entity;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="CONTRIBUTED_TO")
public class Contribution {

	@GraphId
	private Long id;
	
	@StartNode
	private Person artist;
	
	@EndNode
	private Artwork artwork;

	public Contribution(Person p, Artwork a) {
		this.artist = p;
		this.artwork = a;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getArtist() {
		return artist;
	}

	public void setArtist(Person artist) {
		this.artist = artist;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}
	
}
