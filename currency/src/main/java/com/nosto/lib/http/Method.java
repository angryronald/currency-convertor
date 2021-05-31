/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

/**
 *
 * @author angryronald
 */
public enum Method {
    GET ("GET"), POST ("POST"), PUT ("PUT"), PATCH ("PATCH"), DELETE ("DELETE");
    
    private String name;
    private Method(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    } 
}