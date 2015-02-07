package io.larkin.tatesocial.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.larkin.tatesocial.entity.Artist;
import io.larkin.tatesocial.entity.Artwork;
import io.larkin.tatesocial.entity.Movement;
import io.larkin.tatesocial.repository.ArtistRepository;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class ArtistServiceTest {

	private ArtistService artistService;
	private ArtistRepository repoMock;
	
	@Before
	public void setUp() throws Exception {
		repoMock = mock(ArtistRepository.class);
		
		Artist artist = new Artist("Artist1");
		artist.setId(new Long(1));
		
		Movement movement = new Movement("Movement1");
		movement.setId(new Long(1));
		Set<Movement> movements = new HashSet<Movement>();
		movements.add(movement);
		
		Artwork artwork = new Artwork("Artwork1");
		artwork.setId(new Long(1));
		artist.contributedTo(artwork);
		
		List<Artist> artists = new ArrayList<Artist>();
		artists.add(artist);
		
		PageRequest page = new PageRequest(1,10);
		Page<Artist> artistsPage = new PageImpl<Artist>(artists);
		
		when(repoMock.save(artist)).thenReturn(artist);
		when(repoMock.findAll()).thenReturn(artistsPage);
		when(repoMock.findByName("Artist1")).thenReturn(artist);
		when(repoMock.findById(new Long(1))).thenReturn(artist);
		when(repoMock.findAll(page)).thenReturn(artistsPage);

		artistService = new ArtistServiceImpl(repoMock);
	}

	@Test
	public void testGetArtistByName() {
		Artist a = artistService.getArtistByName("Artist1");
		assertEquals(new Long(1), a.getId());
	}
	
	@Test
	public void testGetArtistById() {
		Artist a = artistService.getArtistById(new Long(1));
		assertEquals("Artist1", a.getName());
	}

	@Test
	public void testContributeToArtwork() {
		Artist artist = new Artist("Artist2");
		artist.setId(new Long(2));
		Artwork artwork = new Artwork("Artwork2");
		artwork.setId(new Long(2));
		artistService.contributeToArtwork(artist, artwork);
		
		assertTrue(artist.getArtworks().contains(artwork));
	}
	
	@Test
	public void testSaveArtist() {
		Artist artist = new Artist("Artist2");
		artist.setId(new Long(2));
		artistService.saveArtist(artist);
		assertEquals(new Long(2), artist.getId());
	}
	
	@Test
	public void testGetArtistPage() {
		PageRequest page = new PageRequest(1,10);
		Page<Artist> artists = artistService.getArtistPage(page);
		Artist a = artists.getContent().get(0);
		assertEquals(1, artists.getTotalPages());
		assertEquals(1, artists.getNumberOfElements());
		assertEquals(new Long(1), a.getId());
	}
}
