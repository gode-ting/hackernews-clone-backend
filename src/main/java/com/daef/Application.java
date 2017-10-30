package com.daef;

import com.daef.models.ApplicationUser;
import com.daef.models.Post;
import com.daef.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.daef.repositories.ApplicationUserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableMongoRepositories("com.daef.repositories")

@SpringBootApplication
@EnableAutoConfiguration
//(scanBasePackages = {"com.daef.repositories", "controllers","security","com.daef.models"})
public class Application implements CommandLineRunner {

    @Autowired
    private PostRepository repository;
    
    @Autowired
    private ApplicationUserRepository user;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
    
    @Override
    public void run(String... args) throws Exception {
        user.deleteAll();
        
       
        repository.deleteAll();
        user.save(new ApplicationUser("admin", "password"));
        user.save(new ApplicationUser("admin2", "password"));
        // save a couple of articles
//        repository.save(new Post("Charlie", "story", "aioCQsy3E", "Student Guide 101", "Cool stuff", -1, 10));
//        repository.save(new Post("Frank", "story", "aioCQsy3E", "Student Guide 102", "Bad  stuff", -1, 2));
          System.out.println("Up and running");
        // fetch all articles
//        System.out.println("Articles found with findAll():");
//        System.out.println("-------------------------------");
//        repository.findAll().forEach((post) -> {
//            System.out.println(post.PostText);
//        });
//        System.out.println();

    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
