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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    public JSONObject getAllChildPostByID(int id) {
        //the query to use
        Query query = new Query(Criteria.where("HanesstID").is(id));
        
        //result object. returns this in the end.
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        
        //the first level of comments. the original comments on the post
        //NOT any comments on comments. yet.
        List<Post> list = mongoTemplate.find(query, Post.class);
        //loop over the list of comments
        for (int i = 0; i < list.size(); i++) {
            Post p = list.get(i);
            JSONObject o = recursiveCall(p);
            jsonArray.add(o);
        }
        //add the array to the result and return it
        result.put("Comments", jsonArray);
        
        return result;
    }
    
    private JSONObject recursiveCall(Post p){
        JSONObject o = new JSONObject();
        
        //set the posts variables to the JsonObject
        o.put("HanesstID", p.hanesstID);
        o.put("PostParent", p.postParent);
        o.put("PostText", p.postText);
        o.put("PostTitle", p.postTitle);
        o.put("PostType", p.postType);
        o.put("Pwd", p.pwd);
        o.put("id", p.id);
        o.put("userName", p.userName);
        
        //get all the comments to the parent
        Query query = new Query(Criteria.where("PostParent").is(p.hanesstID));
        
        //put into a list
        List<Post> tmpList = mongoTemplate.find(query, Post.class);
        
        //assert a array
        JSONArray tmpJSONArray = new JSONArray();
        
        //loop throu the list and do a recursive call which returns an object with array inside
        //the array consist of child comments of the current comment
        for (int i = 0; i < tmpList.size(); i++) {
            Post tmpP = tmpList.get(i);
            JSONObject tmpO = recursiveCall(tmpP);
            tmpJSONArray.add(tmpO);
        }
        
        //add the child comments and return the object
        o.put("ChildComments", tmpJSONArray);
        return o;
    }
    
}
