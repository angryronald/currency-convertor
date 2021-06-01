/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

import com.nosto.lib.csrf.CSRF;
import com.nosto.lib.csrf.CSRFNotValidException;
import com.nosto.lib.csrf.CSRFIsEmptyException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author angryronald
 */
public class Middleware {
    // can be extend by adding bearer token or authorization process later on
    /**
     * This function now only proceed 3 conditions:
     * 1. CSRF Token is empty
     * 2. CSRF Token is not found
     * 3. CSRF Token is already expired (more than 5 minutes)
    */
    public static boolean IsCSRFValid(String CSRFToken) throws Exception {
        if (CSRFToken == "") {
            throw new CSRFIsEmptyException();
        }
        
        if (!CSRF.IsExists(CSRFToken)){
            throw new CSRFNotValidException();
        }
        
        LocalDateTime tokenCreatedAt = CSRF.Get(CSRFToken);
        long minuteDiff = ChronoUnit.MINUTES.between(tokenCreatedAt, LocalDateTime.now());
        if (minuteDiff > 5) {
            CSRF.Unset(CSRFToken);
            throw new CSRFNotValidException();
        }
        
        CSRF.Unset(CSRFToken);
        return true;
    }
}
