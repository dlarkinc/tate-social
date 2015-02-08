package io.larkin.tatesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.larkin.tatesocial.entity.Gallery;
import io.larkin.tatesocial.entity.User;
import io.larkin.tatesocial.repository.GalleryRepository;

@Service
public class GalleryServiceImpl implements GalleryService {

	public GalleryServiceImpl(GalleryRepository repo) {
		super();
		this.repo = repo;
	}

	public GalleryServiceImpl() { }
	
	@Autowired
	GalleryRepository repo;
	
	public void saveGallery(Gallery g) {
		repo.save(g);
	}

	public Page<Gallery> getGalleryPageForUser(User user, Pageable pageable) {
		return repo.findByUser(user, pageable);
	}

	@Override
	public Gallery getGalleryById(Long id) {
		return repo.findById(id);
	}

}
