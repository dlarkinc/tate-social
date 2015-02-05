package io.larkin.tatesocial;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.AdviceMode;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableNeo4jRepositories(basePackages = "io.larkin.tatesocial.repository")
public class TateSocialApplication extends Neo4jConfiguration {
	
    @Bean(destroyMethod = "shutdown")
    GraphDatabaseService graphDatabaseService() {
    	return new GraphDatabaseFactory().newEmbeddedDatabase("/home/larkin/neo4j/data/import.graph");
    }

//    @Bean(destroyMethod = "shutdown")
//    SpringRestGraphDatabase graphDatabaseService() {
//    	return new SpringRestGraphDatabase("http://localhost:7474/db/data");
//    }
    
    public TateSocialApplication() {
    	setBasePackage("io.larkin.tatesocial");
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TateSocialApplication.class, args);
    }
}
