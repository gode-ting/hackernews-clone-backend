/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import org.json.simple.JSONObject;

/**
 *
 * @author Frederik
 */
public interface PostInterface {
    public JSONObject getAllChildPostByID(int id);
    public JSONObject getAllComments();
}
