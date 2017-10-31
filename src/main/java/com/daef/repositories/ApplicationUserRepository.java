/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.repositories;

import com.daef.models.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author emilgras
 */
@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
    
    ApplicationUser findByUsername(String username);
    
}
