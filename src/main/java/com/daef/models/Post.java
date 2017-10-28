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
    
    public String userName;
    
    public String PostType;
    
    public String Pwd;
    
    public String PostTitle;
    
    public String PostText;
    
    public int PostParent;
    
    public int HanesstID; 
     
    

    public Post(String userName, String PostType, String Pwd, String PostTitle, String PostText, int PostParent, int HanesstID) {
        this.userName = userName;
        this.PostType = PostType;
        this.Pwd = Pwd;
        this.PostTitle = PostTitle;
        this.PostText = PostText;
        this.PostParent = PostParent;
        this.HanesstID = HanesstID;
     
    }
    

}
