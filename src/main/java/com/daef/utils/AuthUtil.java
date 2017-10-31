/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.utils;

import com.daef.models.ApplicationUser;
import constants.Constants;
import static constants.Constants.MIN_PASSWORD_LENGTH;
import static constants.Constants.MIN_USERNAME_LENGTH;
import org.json.simple.JSONObject;

/**
 *
 * @author emilgras
 */
public class AuthUtil {

    
    
    
    public static enum CredStatus {
        PASSWORD_TO_SHORT,
        USERNAME_TO_SHORT,
        OK
    }

    public static boolean credentialsAreValid(ApplicationUser user) {
        if (checkCredentials(user).equals(CredStatus.OK)) {
            return true;
        }
        return false;
    }

    public static JSONObject getErrorMessage(ApplicationUser user) {
        JSONObject responseMessage = new JSONObject();
        String message = "";
        switch (checkCredentials(user)) {
            case PASSWORD_TO_SHORT: message = "Password should be at least 8 characters"; break;          
            case USERNAME_TO_SHORT: message = "Username should be at least 8 characters"; break;
        }
        responseMessage.put("message", message);
        return responseMessage;
    }

    private static CredStatus checkCredentials(ApplicationUser user) {
        if (user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            return CredStatus.PASSWORD_TO_SHORT;
        }
        if (user.getUsername().length() < MIN_USERNAME_LENGTH) {
            return CredStatus.USERNAME_TO_SHORT;
        }
        return CredStatus.OK;
    }

}
