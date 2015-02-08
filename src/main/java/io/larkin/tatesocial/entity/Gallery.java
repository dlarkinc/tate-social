package io.larkin.tatesocial.entity;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Gallery {

	@GraphId private Long id;
	
	private String name;

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
	
	@RelatedTo(type = "LIST_ARTWORK",direction=Direction.OUTGOING)
	private @Fetch Set<Artwork> artworks;
	
	public Set<Artwork> getArtworks() {
		return artworks;
	}
	
	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Gallery) {
	        Gallery that = (Gallery) obj;
	        return this.id.equals(that.id);
	    }
	    return false;
	}
	
	@RelatedTo(type = "HAS_GALLERY", direction=Direction.INCOMING)
	private @Fetch User user;
	
	public User getUser() {
		return user;
	}
}
