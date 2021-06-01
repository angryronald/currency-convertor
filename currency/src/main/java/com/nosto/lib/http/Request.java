/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.util.Map;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author angryronald
 */
public class Request extends Client implements RequestInterface {
    public Request(){}
    
    @Override
    public String DoRequest(Method method, String urlPath, Map<String, String> parameters, Map<String, String> requestBody) throws Exception{
        URL url = new URL(super.GenerateURL(urlPath, parameters).replaceAll(" ", ""));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method.name());
        
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyInString = objectMapper.writeValueAsString(requestBody);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = requestBodyInString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        
        int status = con.getResponseCode();
        StringBuffer response = new StringBuffer();
        
        if (status <= 299) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            throw new Exception("Error while calling ["+method.name()+"] "+super.GenerateURL(urlPath, parameters).replaceAll(" ", "")+": "+response.toString());
        }

        con.disconnect();
        
        return response.toString();
    }
}
