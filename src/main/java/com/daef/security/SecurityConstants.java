/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.security;

/**
 *
 * @author emilgras
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs"; // Bad idea to place it here - we know
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/signup";
}
