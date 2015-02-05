package io.larkin.tatesocial.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

@NodeEntity
public class User {

	public User() {}
	
	@GraphId private Long id;
	
    private static final String SALT = "dsfasdfsfascv77km";
    public static final String FRIEND = "FRIEND";
	
	@Indexed(unique=true)
	private String login;
	
	private String name;
	
	private String password;
	
	private Roles[] roles;

    public User(String login, String name, String password, Roles... roles) {
        this.login = login;
        this.name = name;
        this.password = encode(password);
        this.roles = roles;
    }
    
    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, SALT);
    }
    
    @RelatedTo(type = FRIEND, direction = Direction.BOTH)
    @Fetch Set<User> friends;

    public void addFriend(User friend) {
        this.friends.add(friend);
    }
    
	public Roles[] getRoles() {
		return roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@RelatedTo(type = "APPRECIATES", direction=Direction.OUTGOING)
	private @Fetch Set<Artist> artists;
	
	public Set<Artist> getArtists() {
		return artists;
	}

	public void contributedTo(Artist artist) {
		if (artists == null) {
			artists = new HashSet<Artist>();
		}
		artists.add(artist);
	}
	
    public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
    
    public void updatePassword(String old, String newPass1, String newPass2) {
        if (!password.equals(encode(old))) throw new IllegalArgumentException("Existing Password invalid");
        if (!newPass1.equals(newPass2)) throw new IllegalArgumentException("New Passwords don't match");
        this.password = encode(newPass1);
    }
}
