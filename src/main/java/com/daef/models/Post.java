/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
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
    
    @JsonProperty("hanesst_id")
    public int hanesstID; 
     
    

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
    
    public Post(String userName, String PostType, String Pwd, String PostTitle, String PostText, String URL, int PostParent, int HanesstID) {
        this.userName = userName;
        this.postType = PostType;
        this.pwd = Pwd;
        this.postTitle = PostTitle;
        this.postText = PostText;
        this.url = URL;
        this.postParent = PostParent;
        this.hanesstID = HanesstID;
     
    }

    public Post(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
