/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.security;

import com.daef.models.ApplicationUser;
import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.daef.repositories.ApplicationUserRepository;

/**
 *
 * @author emilgras
 *
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    } 

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     *
     * When a user tries to authenticate (login), this method receives the username,
     * searches the database for a record containing it, and (if found) returns
     * an instance of User. The properties of this instance (username and
     * password) are then checked against the credentials passed by the user in
     * the login request. This last process is executed outside this class, by
     * the Spring Security framework.
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("------------ load user ---------------");
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
