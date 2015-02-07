package io.larkin.tatesocial.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Artist {
	
	public Artist() {}
    public Artist(String name) { this.name = name; }
	
    @GraphId private Long id;
    
    @Indexed(unique = false)
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
    
	@RelatedTo(type = "CONTRIBUTED_TO",direction=Direction.OUTGOING)
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
	
	public void joined(Movement movement) {
		if (movements == null) {
			movements = new HashSet<Movement>();
		}
		movements.add(movement);
	}
	
	@RelatedTo(type = "APPRECIATES", direction = Direction.INCOMING)
	private Set<User> users;
	
	public Set<User> getUsers() {
		return users;
	}
	
	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Artist) {
	        Artist that = (Artist) obj;
	        return this.id.equals(that.id);
	    }
	    return false;
	}
}
