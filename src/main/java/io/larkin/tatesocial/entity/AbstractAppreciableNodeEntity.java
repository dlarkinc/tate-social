package io.larkin.tatesocial.entity;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.RelatedTo;

public abstract class AbstractAppreciableNodeEntity extends AbstractNodeEntity {
	
	@RelatedTo(type = "APPRECIATES", direction = Direction.INCOMING)
	private Set<User> users;
	
	public Set<User> getUsers() {
		return users;
	}
	
	@Override
	public int hashCode() {
	    return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		AbstractNodeEntity that = (AbstractNodeEntity)obj;
	    return this.getId().equals(that.getId());
	}
}
