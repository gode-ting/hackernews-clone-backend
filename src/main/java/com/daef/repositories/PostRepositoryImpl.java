/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import com.daef.models.Post;
import com.mongodb.WriteResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
        //the query to use
        Query query = new Query(Criteria.where("PostParent").is(id));
        
        //the end result. adds to this object, then returns it.
        List<Post> result = new ArrayList();
        
        //the first level of comments. the original comments on the post
        //NOT any comments on comments. yet.
        List<Post> list = mongoTemplate.find(query, Post.class);
        
        //a stack to easily add and remove from.
        Stack<Post> stack = new Stack();
        stack.addAll(list);
        
        //loop until the stack is empty
        while(!stack.isEmpty()){
            Post p = stack.pop();
            result.add(p);
            query = new Query(Criteria.where("PostParent").is(p.HanesstID));
            List<Post> tmpList = mongoTemplate.find(query, Post.class);
            stack.addAll(tmpList);
            
        }
        
        
    }
    
}
