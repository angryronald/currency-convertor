/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.csrf;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author angryronald
 */
public class CSRF {

    private String Token;
    private LocalDateTime CreatedAt;
    private LocalDateTime ExpiredAt;
    
    public CSRF(String Token, LocalDateTime CreatedAt, LocalDateTime ExpiredAt){
        this.Token = Token;
        this.CreatedAt = CreatedAt;
        this.ExpiredAt = ExpiredAt;
    }
    /**
     * @return the Token
     */
    public String getToken() {
        return Token;
    }

    /**
     * @param Token the Token to set
     */
    public void setToken(String Token) {
        this.Token = Token;
    }

    /**
     * @return the CreatedAt
     */
    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    /**
     * @param CreatedAt the CreatedAt to set
     */
    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    /**
     * @return the ExpiredAt
     */
    public LocalDateTime getExpiredAt() {
        return ExpiredAt;
    }

    /**
     * @param ExpiredAt the ExpiredAt to set
     */
    public void setExpiredAt(LocalDateTime ExpiredAt) {
        this.ExpiredAt = ExpiredAt;
    }
    
    private static Map<String, LocalDateTime> ActiveCSRF = new HashMap<String, LocalDateTime>();
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public static String GenerateToken(LocalDateTime now) throws Exception {
        String token = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(now.toString().getBytes(StandardCharsets.UTF_8));
            String hashInString = CSRF.bytesToHex(encodedhash);
            Random random = new Random();
            int randomNumber = random.nextInt(123) + 123;
            token = token + Integer.toString(randomNumber) + hashInString;
        } catch (Exception e) {
            throw new Exception("Error in GenerateToken: "+e);
        }
        
        return token;
    }
    
    public static void Set(String token){
        CSRF.ActiveCSRF.put(token, LocalDateTime.now());
    }
    
    public static LocalDateTime Get(String token) {
        return CSRF.ActiveCSRF.get(token);
    }
    
    public static void Unset(String token) {
        CSRF.ActiveCSRF.remove(token);
    }
    
    public static boolean IsExists(String token) {
        return CSRF.ActiveCSRF.containsKey(token);
    }
}
