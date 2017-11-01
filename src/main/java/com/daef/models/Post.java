/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 *
 * @author abj
 */

public class Post {
    @Id
    public String id;
    
    @JsonProperty("username")
    public String userName;
    
    @JsonProperty("post_type")
    public String postType;
    
    @JsonProperty("pwd_hash")
    public String pwd;
    
    @JsonProperty("post_title")
    public String postTitle;
    
    @JsonProperty("post_text")
    public String postText;
    
    @JsonProperty("post_url")
    public String url;
    
    @JsonProperty("post_parent")
    public int postParent;
    
    @JsonProperty("parent")
    public String parent;
    
    @JsonProperty("hanesst_id")
    public int hanesstID; 
    
    @JsonProperty("upvotedBy")
    public ArrayList<String> upvotedBy; 
    
    @JsonProperty("downvotedBy")
    public ArrayList<String> downvotedBy; 
    
    @JsonProperty("karma")
    public int karma;
    
    @JsonProperty("created_at")
    public Date createdAt;
    

//    public Post(String userName, String PostType, String Pwd, String PostTitle, String PostText, int PostParent, int HanesstID) {
//        this.userName = userName;
//        this.PostType = PostType;
//        this.Pwd = Pwd;
//        this.PostTitle = PostTitle;
//        this.PostText = PostText;
//        this.PostParent = PostParent;
//        this.HanesstID = HanesstID;
//     
//    }
    
    public Post(String userName, String PostType, String Pwd, String PostTitle, String PostText, String URL, String parent, int HanesstID) {
        this.userName = userName;
        this.postType = PostType;
        this.pwd = Pwd;
        this.postTitle = PostTitle;
        this.postText = PostText;
        this.url = URL;
        this.parent = parent;
        this.hanesstID = HanesstID;
        this.upvotedBy = new ArrayList();
        this.downvotedBy = new ArrayList();
        this.karma = 0;
    }

    public Post(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String PostType) {
        this.postType = PostType;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String Pwd) {
        this.pwd = Pwd;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String PostTitle) {
        this.postTitle = PostTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String PostText) {
        this.postText = PostText;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = URL;
    }

    public int getPostParent() {
        return postParent;
    }

    public void setPostParent(int PostParent) {
        this.postParent = PostParent;
    }

    public int getHanesstID() {
        return hanesstID;
    }

    public void setHanesstID(int HanesstID) {
        this.hanesstID = HanesstID;
    }
    
    
    
    
    
}
