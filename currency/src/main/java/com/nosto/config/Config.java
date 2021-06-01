/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.config;

import java.util.Map;

/**
 *
 * @author angryronald
 */
public class Config {
    private final String ExposedPort = "EXPOSED_PORT";
    private final Map<String, String> env;
    private final String RegisteredHost = "REGISTERED_HOST";
    private final String ExchangeRatesAPIAccessKey = "EXCHANGERATES_API_ACCESS_KEY";
    private final String ExchangeRatesAPIURL = "EXCHANGERATES_API_URL";
    
    public Config(){
        this.env = System.getenv();
    }
    
    public Integer GetExposedPort(){ 
        String portInString = "8001";
        if (this.env.get(this.ExposedPort)!= null && !this.env.get(this.ExposedPort).equals("")) {
            portInString = this.env.get(this.ExposedPort);
        }
        
        Integer port = 8001;
        try {
            port = Integer.parseInt(portInString);
        } catch(Exception e) {
            System.err.println("ExposedPort in Env Variable supposed to be number: "+e);
        } finally {
            System.out.println("Service is running on port :"+ port);
        }
        
        return port;
    }
    
    public String GetRegisteredHost(){
        String host = "127.0.0.1";
        if (this.env.get(this.RegisteredHost)!= null && !this.env.get(this.RegisteredHost).equals("")) {
            host = this.env.get(this.RegisteredHost);
        }
        
        return host;
    }
    
    public String GetExchangeRatesAPIAccessKey(){
        String accessKey = "d774eeef0d7928e7920a53050c0623f1";
        if (this.env.get(this.ExchangeRatesAPIAccessKey)!= null && !this.env.get(this.ExchangeRatesAPIAccessKey).equals("")) {
            accessKey = this.env.get(this.ExchangeRatesAPIAccessKey);
        }
        
        return accessKey;
    }
    
    public String GetExchangeRatesAPIURL(){
        String apiUrl = "http://api.exchangeratesapi.io/v1";
        if (this.env.get(this.ExchangeRatesAPIURL)!= null && !this.env.get(this.ExchangeRatesAPIURL).equals("")) {
            apiUrl = this.env.get(this.ExchangeRatesAPIURL);
        }
        
        return apiUrl;
    }
}
