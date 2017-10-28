/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.models;

import org.springframework.data.annotation.Id;

/**
 *
 * @author abj
 */
public class Post {
       @Id
    public String id;
    
    public String Username;
    
    public String PostType;
    
    public String Pwd;
    
    public String PostTitle;
    
    public String PostText;
    
    public int PostParent;
    
    public int HanesstID; 
     
    

    public Post(String username, String post_type, String pwd_hash, String post_title, String post_text, int post_parent, int hanesst_id) {
        this.Username = username;
        this.PostType = post_type;
        this.Pwd = pwd_hash;
        this.PostTitle = post_title;
        this.PostText = post_text;
        this.PostParent = post_parent;
        this.HanesstID = hanesst_id;
     
    }
    

}
