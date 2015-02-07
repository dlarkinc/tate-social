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
    public static final String FRIEND_OF = "FRIEND_OF";
    public static final String HAS_GALLERY = "HAS_GALLERY";
    public static final String APPRECIATES_ARTIST = "APPRECIATES_ARTIST";
    public static final String APPRECIATES_ARTWORK = "APPRECIATES_ARTWORK";
	
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
    
    @RelatedTo(type = FRIEND_OF, direction = Direction.BOTH)
    @Fetch private Set<User> friends;

    public void befriend(User friend, boolean flag) {
    	if (flag) {
    		this.friends.add(friend);
    	} else {
    		this.friends.remove(friend);
    	}
    }
    
	public Set<User> getFriends() {
		return friends;
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
	
	@RelatedTo(type = APPRECIATES_ARTIST, direction=Direction.OUTGOING)
	private Set<Artist> artists;
	
	public Set<Artist> getArtists() {
		return artists;
	}

	@RelatedTo(type = APPRECIATES_ARTWORK, direction=Direction.OUTGOING)
	private Set<Artwork> artworks;
	
	public Set<Artwork> getArtworks() {
		return artworks;
	}
	
    public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (id == null) return super.equals(o);
        return id.equals(user.id);

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
    
    public void appreciates(Artist artist, boolean flag) {
    	if (flag) {
    		this.artists.add(artist);
    	} else {
    		this.artists.remove(artist);
    	}
    }

    public void appreciates(Artwork artwork, boolean flag) {
    	if (flag) {
    		this.artworks.add(artwork);
    	} else {
    		this.artworks.remove(artwork);
    	}
    }

	@RelatedTo(type = HAS_GALLERY, direction=Direction.OUTGOING)
	private @Fetch Set<Gallery> galleries;
	
	public Set<Gallery> getGalleries() {
		return galleries;
	}
}
