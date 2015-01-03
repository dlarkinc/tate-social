package io.larkin.tatesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.larkin.tatesocial.model.Artist;
import io.larkin.tatesocial.model.Artwork;

public interface ArtistService {
	
	Artist getArtistByName(String name);
	
	void saveArtist(Artist artist);
	
	void contributeToArtwork(Artist artist, Artwork artwork);
	
	Page<Artist> getArtistPage(Pageable pageable);
}
