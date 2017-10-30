/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import com.daef.models.Post;
import com.mongodb.WriteResult;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
/**
 *
 * @author Frederik
 */
public class PostRepositoryImpl implements PostInterface {

    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public void getAllPostByParentID(int id) {
        Query query = new Query(Criteria.where("PostParent").is(id));
        
        List<Post> list = mongoTemplate.find(query, Post.class);
        
        System.out.println("size: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).PostTitle);
        }
        System.out.println("done");
    }
    
}
