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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abj
 */
@RestController
@RequestMapping("/latest")
public class LatestDigestedPostController {
   
     @Autowired
    private PostRepository repository;
    
    
     @RequestMapping(method = RequestMethod.GET)
    public int getLatest(){
        List<Post> maxObject = repository.findAll(new Sort(Sort.Direction.DESC, "hanesstID"));
      
        //return "Hello";
        return maxObject.get(0).getHanesstID();

     
    }
}
