package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.larkin.tatesocial.entity.Artist;
import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	ArtistRepository repo;

	public Artist getArtistByName(String name) {
		return repo.findByName(name);
	}
	
	public void saveArtist(Artist p) {
		repo.save(p);
	}

	public void contributeToArtwork(Artist artist, Artwork artwork) {
		artist.contributedTo(artwork);
       	repo.save(artist);
	}

	public Page<Artist> getArtistPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Artist getArtistById(Long id) {
		return repo.findById(id);
	}
}
