package io.larkin.tatesocial.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
//import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ArtistRepositoryTest {

	@Configuration
	@EnableNeo4jRepositories
	static class TestConfig extends Neo4jConfiguration {
		TestConfig() throws ClassNotFoundException {
			setBasePackage("io.larkin.tatesocial.entity", "io.larkin.tatesocial.repository");
		}
//		@Bean
//		GraphDatabaseService graphDatabaseService() {
//			//return new TestGraphDatabaseFactory().newImpermanentDatabase();
//		}
	}
	@Autowired
	ArtistRepository repo;
	
	GraphDatabaseService graphDatabaseService;
	
	@Before
	public void setUp() throws Exception {
		graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase("/home/larkin/neo4j/data/import.graph");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllPageable() {
		fail("Not yet implemented");
	}

}
