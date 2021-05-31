/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.infrastructure.external.http;

import com.nosto.internal.currency.infrastructure.external.ExternalCurrency;
import com.nosto.internal.currency.infrastructure.external.ConverterExternal;
import com.nosto.lib.http.Method;
import com.nosto.lib.http.Request;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/**
 *
 * @author angryronald
 */
public class ConverterHttp implements ConverterExternal {
    private Request client;
    
    public ConverterHttp(Request client) {
        this.client = client;
    }
    
    @Override
    public ExternalCurrency Convert(ExternalCurrency source, String code) throws Exception{
        String urlPath = "/convert";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("from", source.GetCode());
        parameters.put("to", code);
        parameters.put("amount", source.GetAmount().toString());
        
        String response = this.client.DoRequest(Method.GET, urlPath, parameters, new HashMap<String, String>());
        JSONObject jsonObject = new JSONObject(response);
        ExternalCurrency result = new ExternalCurrency(code,jsonObject.getDouble("result"));
        
        return result;
    }
}
