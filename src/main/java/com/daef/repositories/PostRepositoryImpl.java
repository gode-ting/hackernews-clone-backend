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
        result.put("Comments", jsonArray);
        
        System.out.println(result.toJSONString());
        
        return result;
    }
    
    private JSONObject recursiveCall(Post p){
        JSONObject o = new JSONObject();
        o.put("HanesstID", p.HanesstID);
        o.put("PostParent", p.PostParent);
        o.put("PostText", p.PostText);
        o.put("PostTitle", p.PostTitle);
        o.put("PostType", p.PostType);
        o.put("Pwd", p.Pwd);
        o.put("id", p.id);
        o.put("userName", p.userName);
        
        Query query = new Query(Criteria.where("PostParent").is(p.HanesstID));
        List<Post> tmpList = mongoTemplate.find(query, Post.class);
        JSONArray tmpJSONArray = new JSONArray();
        for (int i = 0; i < tmpList.size(); i++) {
            Post tmpP = tmpList.get(i);
            JSONObject tmpO = recursiveCall(tmpP);
            tmpJSONArray.add(tmpO);
        }
        o.put("ChildComments", tmpJSONArray);
        
        return o;
    }
    
}
