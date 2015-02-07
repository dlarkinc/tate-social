package io.larkin.tatesocial.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import io.larkin.tatesocial.entity.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO: Find a way to create some nodes and edges from something like an XML file rather than programmatically
 * 
 * @author larkin
 *
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ArtistRepositoryTest {
	
	@Autowired
    private Neo4jTemplate template;
	
	@Configuration
	@EnableNeo4jRepositories
	static class TestConfig extends Neo4jConfiguration {
		TestConfig() throws ClassNotFoundException {
			setBasePackage("io.larkin.tatesocial.entity", "io.larkin.tatesocial.repository");
		}
		@Bean
        GraphDatabaseService graphDatabaseService() {
            return new TestGraphDatabaseFactory().newImpermanentDatabase();
        }
	}
	
	@Autowired
	private ArtistRepository repo;
	
	@Autowired
	private GraphDatabaseService graphDatabaseService;
	
	@Before
	public void setUp() throws Exception {
		Neo4jHelper.cleanDb(graphDatabaseService, false);
		Transaction tx = graphDatabaseService.beginTx();
		try {
			HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("name", "Johnstone, William");
			ArrayList<String> labels = new ArrayList<String>();
			labels.add("Artist");
			labels.add("_Artist");
			template.createNode(properties, labels);
			tx.success();
		} finally {
			tx.close();
		}
	}

	@Test
	public void testFindByName() {
		Artist a = repo.findByName("Johnstone, William");
		String name = a.getName();
		assertEquals(name, "Johnstone, William");
	}
	
	@Test
	public void testFindAll() {
		Page<Artist> artists = repo.findAll(new PageRequest(0, 10));
		assertEquals(1, artists.getTotalPages());
	}
}
