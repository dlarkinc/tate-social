package io.larkin.tatesocial.service;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.larkin.tatesocial.model.Artist;
import io.larkin.tatesocial.model.Artwork;
import io.larkin.tatesocial.repository.ArtistRepository;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	ArtistRepository repo;
	
	@Autowired
    GraphDatabase graphDatabase;
	
	public Artist getArtistByName(String name) {
		return repo.findByName(name);
	}
	
	public void saveArtist(Artist p) {
		Transaction tx = graphDatabase.beginTx();
        try {
        	repo.save(p);
        	tx.success();
        } finally {
        	tx.close();
        }
	}

	public void contributeToArtwork(Artist artist, Artwork artwork) {
		artist.contributedTo(artwork);
		Transaction tx = graphDatabase.beginTx();
		try {
        	repo.save(artist);
        	tx.success();
        } finally {
        	tx.close();
        }
	}

	@Override
	public Page<Artist> getArtistPage(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
