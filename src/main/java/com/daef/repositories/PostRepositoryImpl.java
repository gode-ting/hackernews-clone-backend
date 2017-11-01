/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import com.daef.models.ApplicationUser;
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
import org.springframework.data.mongodb.core.query.Update;

/**
 *
 * @author Frederik
 */
public class PostRepositoryImpl implements PostInterface {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public JSONObject getAllChildPostByID(String id) {
        //the query to use
        Query query = new Query(Criteria.where("id").is(id));

        //result object. returns this in the end.
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //the first level of comments. the original comments on the post
        //NOT any comments on comments. yet.
        List<Post> list = mongoTemplate.find(query, Post.class);
        //loop over the list of comments
        for (int i = 0; i < list.size(); i++) {
            Post p = list.get(i);
            JSONObject o = recursiveCallForGetAllChildPostByID(p);
            jsonArray.add(o);
        }
        //add the array to the result and return it
        result.put("original-post", list.get(0));
        result.put("Comments", jsonArray);

        return result;
    }

    private JSONObject recursiveCallForGetAllChildPostByID(Post p) {
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
        Query query = new Query(Criteria.where("parent").is(p.hanesstID));

        //put into a list
        List<Post> tmpList = mongoTemplate.find(query, Post.class);

        //assert a array
        JSONArray tmpJSONArray = new JSONArray();

        //loop throu the list and do a recursive call which returns an object with array inside
        //the array consist of child comments of the current comment
        for (int i = 0; i < tmpList.size(); i++) {
            Post tmpP = tmpList.get(i);
            JSONObject tmpO = recursiveCallForGetAllChildPostByID(tmpP);
            tmpJSONArray.add(tmpO);
        }

        //add the child comments and return the object
        o.put("ChildComments", tmpJSONArray);
        return o;
    }

    @Override
    public JSONObject getAllComments() {
        //the query to find all comments
        Query query = new Query(Criteria.where("PostType").is("comment"));

        //assert the result object and the array which will be in the result
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //get all the posts form the database that are comments
        List<Post> list = mongoTemplate.find(query, Post.class);

        //loop throu the list
        for (int i = 0; i < list.size(); i++) {
            Post p = list.get(i);
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
            
            //add the jsonObject to the jsonArray
            jsonArray.add(o);
        }
        
        //add the jsonArray to the result under "comments". and return result.
        result.put("comments", jsonArray);
        return result;
    }

    @Override
    public void upvotePost(String id, String username){
        
        //find the post by id
         Query query = new Query(Criteria.where("id").is(id));
         Post p = mongoTemplate.findOne(query, Post.class);
         System.out.println("111");
         System.out.println("title: " + p.getPostTitle());
         System.out.println("2222");
         //find the user by hes username
         Query query2 = new Query(Criteria.where("username").is(username));
         ApplicationUser user = mongoTemplate.findOne(query2, ApplicationUser.class);
         System.out.println("password: " + user.getPassword());
         
         //check if the user already upvoted this post
         if(!p.upvotedBy.contains(user.getUsername())){
             user.setKarma(user.getKarma() + 1);
             p.upvotedBy.add(user.getUsername());
             p.setKarma(p.getKarma() + 1);
             if(p.downvotedBy.contains(user.getUsername())){
                 p.downvotedBy.remove(user.getUsername());
                 p.setKarma(p.getKarma() + 1);
             }
             mongoTemplate.save(p);
             mongoTemplate.save(user);
         } else {
             System.out.println(user.getUsername() + " already upvoted that post");
         }
         
         
    }
    
    @Override
    public void downvotePost(String id, String username){
        
        //find the post by id
         Query query = new Query(Criteria.where("id").is(id));
         Post p = mongoTemplate.findOne(query, Post.class);
         System.out.println("title: " + p.getPostTitle());
         
         //find the user by hes username
         Query query2 = new Query(Criteria.where("username").is(username));
         ApplicationUser user = mongoTemplate.findOne(query2, ApplicationUser.class);
         System.out.println("password: " + user.getPassword());
         
         //check if the user already upvoted this post
         if(!p.downvotedBy.contains(user.getUsername())){
             //user.setKarma(user.getKarma() + 1);
             p.downvotedBy.add(user.getUsername());
             p.setKarma(p.getKarma() - 1);
             if(p.upvotedBy.contains(user.getUsername())){
                 p.upvotedBy.remove(user.getUsername());
                 user.setKarma(user.getKarma() - 1);
                 p.setKarma(p.getKarma() - 1);
             }
             mongoTemplate.save(p);
             mongoTemplate.save(user);
         } else {
             System.out.println(user.getUsername() + " already upvoted that post");
         }
         
         
    }
    
}
