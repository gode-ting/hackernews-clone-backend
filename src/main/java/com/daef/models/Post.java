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
    public String PostType;
    
    @JsonProperty("pwd_hash")
    public String Pwd;
    
    @JsonProperty("post_title")
    public String PostTitle;
    
    @JsonProperty("post_text")
    public String PostText;
    
    @JsonProperty("post_url")
    public String URL;
    
    @JsonProperty("post_parent")
    public int PostParent;
    
    @JsonProperty("hanesst_id")
    public int HanesstID; 
     
    

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
        this.PostType = PostType;
        this.Pwd = Pwd;
        this.PostTitle = PostTitle;
        this.PostText = PostText;
        this.URL = URL;
        this.PostParent = PostParent;
        this.HanesstID = HanesstID;
     
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
        return PostType;
    }

    public void setPostType(String PostType) {
        this.PostType = PostType;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String Pwd) {
        this.Pwd = Pwd;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String PostTitle) {
        this.PostTitle = PostTitle;
    }

    public String getPostText() {
        return PostText;
    }

    public void setPostText(String PostText) {
        this.PostText = PostText;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getPostParent() {
        return PostParent;
    }

    public void setPostParent(int PostParent) {
        this.PostParent = PostParent;
    }

    public int getHanesstID() {
        return HanesstID;
    }

    public void setHanesstID(int HanesstID) {
        this.HanesstID = HanesstID;
    }
    
    
    
    
    
}
