/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.models;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;

/**
 *
 * @author emilgras
 */
public class ApplicationUser {
    
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private String username;
    private String password;
    private int karma;
    
    public ApplicationUser() {
    }
    
    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
