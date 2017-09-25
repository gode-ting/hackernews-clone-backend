package com.daef.hackernewsclonebackend;

import com.daef.models.Article;
import com.daef.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories ("com.daef.repositories")

@SpringBootApplication(scanBasePackages = { "com.daef.repositories" })
public class HackernewsCloneBackendApplication implements CommandLineRunner  {

    @Autowired
	private ArticleRepository repository;
    
    
	public static void main(String[] args) {
		SpringApplication.run(HackernewsCloneBackendApplication.class, args);
	}
        
        @Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of articles
		repository.save(new Article("google.dk", "Google's new website!"));
		repository.save(new Article("facebook.com", "Facebook new website!"));

		// fetch all articles
		System.out.println("Articles found with findAll():");
		System.out.println("-------------------------------");
		for (Article customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual article
		System.out.println("Article found with findByHeadline('Facebook new website!'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByHeadline("Facebook new website!"));


	}
}
