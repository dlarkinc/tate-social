package io.larkin.tatesocial;

import io.larkin.tatesocial.dao.UserDao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "io.larkin.tatesocial.repository")
public class TateSocialApplication extends Neo4jConfiguration {
	
//    @Bean
//    GraphDatabaseService graphDatabaseService() {
//    	return new GraphDatabaseFactory().newEmbeddedDatabase("C:\\Users\\larkin.cunningham\\Documents\\Neo4j\\import.graph");
//    }
    @Bean
    SpringRestGraphDatabase graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }   
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
    
//    @Bean
//    UserDao userDao() {
//    	return new UserDao();
//    }
    
    public TateSocialApplication() {
    	setBasePackage("io.larkin.tatesocial");
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TateSocialApplication.class, args);
    }
}
