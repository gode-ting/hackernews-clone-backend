/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.daef.models.Post;
import com.daef.repositories.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author emilgras
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    private PostRepository repository;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getAllPosts(){
      List<Post> posts = repository.findAll();
        //return "Hello";
       return posts;
     
    }
    
}
