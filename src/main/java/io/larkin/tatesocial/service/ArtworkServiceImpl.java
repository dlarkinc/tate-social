package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.repository.ArtworkRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	@Autowired
	ArtworkRepository repo;

	public void saveArtwork(Artwork p) {
		repo.save(p);
	}

	public Page<Artwork> getArtworkPage(Pageable pageable) {
		return repo.findAll(pageable);
	} 

	@Override
	public Artwork getArtworkByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artwork getArtworkById(Long id) {
		return repo.findById(id);
	}
}
