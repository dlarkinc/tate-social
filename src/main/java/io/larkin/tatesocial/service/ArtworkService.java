package io.larkin.tatesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.larkin.tatesocial.entity.Artwork;

public interface ArtworkService {
	
	Artwork getArtworkByTitle(String title);
	
	Artwork getArtworkById(Long id);
	
	void saveArtwork(Artwork artwork);
	
	Page<Artwork> getArtworkPage(Pageable pageable);
}
