package io.larkin.tatesocial.entity;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="APPRECIATES")
public class Appreciation {

	@GraphId
	private Long id;
	
	@StartNode
	private User user;
	
	@EndNode
	private Artist artist;

	public Appreciation(User u, Artist a) {
		this.artist = a;
		this.user = u;
	}
	
	public Appreciation() { }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
