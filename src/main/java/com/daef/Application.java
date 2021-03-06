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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import org.json.simple.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableMongoRepositories("com.daef.repositories")
@SpringBootApplication
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

        //user.deleteAll();
        
       
        //repository.deleteAll();
        //ApplicationUser user1 = new ApplicationUser("admin", "password");
        //user1.setCreatedAt(new Date());
        
        //ApplicationUser user2 = new ApplicationUser("admin2", "password");
        //user1.setCreatedAt(new Date());
        
        //user.save(user1);
        //user.save(user2);

        // save a couple of articles
//        repository.save(new Post("Charlie", "story", "aioCQsy3E", "Student Guide 101", "Cool stuff 0","", "", 10));
//        repository.save(new Post("Frank", "story", "aioCQsy3E", "Student Guide 102", "Bad  stuff","", "", 2));
//        repository.save(new Post("Fred", "comment", "aioCQsy3E", "", "Bad  stuff 1","", "d23qdwe", 3));
//        repository.save(new Post("Phil", "comment", "aioCQsy3E", "", "dumb  stuff 2","", "qw3dq34", 4));
//        repository.save(new Post("Bent", "comment", "aioCQsy3E", "", "sick  stuff 3","", "f34f3", 5));
        System.out.println("Up and running");
    //repository.getAllChildPostByID(10);
    }
    
    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
    }
    
}
