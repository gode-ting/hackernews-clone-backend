/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Frederik
 */
public interface PostInterface {

    public JSONArray getAllChildPostByID(String id);

    public JSONArray getAllComments();

    public void upvotePost(String id, String username);

    public void downvotePost(String id, String username);
}
