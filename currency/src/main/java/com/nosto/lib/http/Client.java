/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author angryronald
 */
public class Client {
    private String BaseURL;
    private Map<String,String> Parameters = new HashMap<String, String>();
    private Map<String,String> Headers = new HashMap<String, String>();
    
    public Client(){}
    
    public void SetBaseURL(String BaseURL) {
        this.BaseURL = BaseURL;
    }
    
    public void AddParameter(String key, String value) {
        this.Parameters.put(key, value);
    }
    
    public void AddHeader(String key, String value) {
        this.Headers.put(key, value);
    }
    
    public Map<String, String> GetHeaders(){
        return this.Headers;
    }
    
    public String GenerateQueryParameters(Map<String,String> parameters){
        String result = "";
        if (parameters.size() > 0) {
            result = "?";
        }
        for (Map.Entry p : parameters.entrySet()){
            result = result + p.getKey() + " = " + p.getValue() + "&";
        }
        if (result.length() > 0)
            result = result.substring(0, result.length()-1);
        
        return result;
    }
    
    private Map<String, String> combineParameters(Map<String,String> first, Map<String,String> second){
        Map<String, String> result = new HashMap<String, String>();
        
        for (Map.Entry p : first.entrySet()){
            result.put(p.getKey().toString(), p.getValue().toString());
        }
        
        for (Map.Entry p : second.entrySet()){
            result.put(p.getKey().toString(), p.getValue().toString());
        }
        
        return result;
    }
    
    public String GenerateURL(String URLPath, Map<String,String> parameters){
        Map<String, String> completeParameters = combineParameters(this.Parameters, parameters);
        return this.BaseURL+ URLPath + this.GenerateQueryParameters(completeParameters);
    }
}
