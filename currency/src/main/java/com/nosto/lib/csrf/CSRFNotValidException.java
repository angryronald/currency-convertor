/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.csrf;

/**
 *
 * @author angryronald
 */
public class CSRFNotValidException extends CSRFException {
    public CSRFNotValidException(){
        super("CSRF token provided is not valid");
    }
}
