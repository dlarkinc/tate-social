package io.larkin.tatesocial.entity;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Artwork {
	
	@GraphId
	private Long id;
	
	private String title;
	
	private String acno;
	
	public Artwork() { }
	public Artwork(String title) { this.title = title; }
	
	@RelatedTo(type = "CONTRIBUTED_TO", direction = Direction.INCOMING)
	@Fetch private Set<Artist> contributors;

	public Set<Artist> getContributors() {
		return contributors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAcno() {
		return acno;
	}

	public void setAcno(String acno) {
		this.acno = acno;
	}
	
	@RelatedTo(type = "APPRECIATES_ARTWORK", direction = Direction.INCOMING)
	@Fetch private Set<User> users;
	
	public Set<User> getUsers() {
		return users;
	}
	
	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Artwork) {
	        Artwork that = (Artwork) obj;
	        return this.id.equals(that.id);
	    }
	    return false;
	}
}
