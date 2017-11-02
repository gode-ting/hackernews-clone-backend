/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef;

import com.daef.models.Post;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abj
 */
@RestController
@RequestMapping(value = "/status")
public class NewServerHealth {
    @Autowired
    HealthEndpoint healthEndpoint;

    
    public Health health() throws IOException {
        Health health = healthEndpoint.invoke();
        return Health.status(health.getStatus()).build();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getStatus() throws IOException{
       
        //return "Hello";
         if (health().getStatus().toString().equals("Alive")) {
             return "Alive";
         }
         if (health().getStatus().toString().equals("UNKNOWN")) {
             return "Update";
         }
        
         return "Down";
    }
}
