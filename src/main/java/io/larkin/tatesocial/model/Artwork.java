package io.larkin.tatesocial.model;

import java.util.Set;

import org.neo4j.graphdb.Direction;
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
	private Set<Person> contributors;

	public Set<Person> getContributors() {
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
}
