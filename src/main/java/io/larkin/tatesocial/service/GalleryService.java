package io.larkin.tatesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.larkin.tatesocial.entity.Gallery;
import io.larkin.tatesocial.entity.User;

public interface GalleryService {
	
	Gallery getGalleryById(Long id);
	
	void saveGallery(Gallery gallery);
	
	Page<Gallery> getGalleryPageForUser(User user, Pageable pageable);
}
