/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.controllers;

import com.daef.models.Post;
import com.daef.repositories.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    
    //CREATE
    //TESTING
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Post post,UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Post " + post.userName);
 
        repository.save(post);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/post/{id}").buildAndExpand(post.id).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
   
    //READ
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable("id") String id){
		
        return repository.findOne(id);
    }
    
    //UPDATE
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Post updatePost(@ModelAttribute("post") Post post){
		
        return repository.insert(post);
    }
   
    //DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("id") String id){
		
       repository.delete(id);
        return "redirect:/persons";
    }
    
    
    
}
