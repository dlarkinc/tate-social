package io.larkin.tatesocial.model;

import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

	@GraphId
	Long id;
	
	//@Indexed(indexName = "search")
	String name;

	@RelatedTo(type = "CONTRIBUTED_TO")
    Set<Artwork> artworks;
	
	public Set<Artwork> getArtworks() {
		return artworks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
