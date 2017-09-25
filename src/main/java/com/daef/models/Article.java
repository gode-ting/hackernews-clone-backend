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
public class Article {  
    @Id
    public String id;
    
    public String link;
    
    public String headline; 
    
    public Article(){}
    
    public Article(String link, String headline){
        this.link = link;
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "Article{" + "link=" + link + ", headline=" + headline + '}';
    }
    
    
}
