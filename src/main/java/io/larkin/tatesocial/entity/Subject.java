package io.larkin.tatesocial.entity;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Subject {

	@GraphId
	private Long id;
	
	@Indexed
	private String name;
	
	@RelatedTo(type = "TYPE_OF",direction=Direction.INCOMING)
	private @Fetch Subject parent;
	
	@RelatedTo(type = "TYPE_OF",direction=Direction.OUTGOING)
	private @Fetch Set<Subject> children;
	
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

	public Subject getParent() {
		return parent;
	}

	public void setParent(Subject parent) {
		this.parent = parent;
	}

	public Set<Subject> getChildren() {
		return children;
	}

	public void setChildren(Set<Subject> children) {
		this.children = children;
	}

}
