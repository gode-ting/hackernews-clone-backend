/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.daef.models.Post;
import com.daef.repositories.PostRepository;
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
    private PostRepository repository;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String getAllPosts(){
      
        return "Hello";
       //return (Post) repository.findAll();
     
    }
    
}
