/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.endpoint;

import com.nosto.internal.currency.application.Application;
import com.nosto.lib.http.Response;
import java.time.LocalDateTime;
import java.util.Map;
import org.rapidoid.job.Jobs;
import org.rapidoid.setup.On;
/**
 *
 * @author angryronald
 * endpoint or controller with registered path
 */
public class Endpoint {
    private Application application;
    
    public Endpoint(Application application){
        this.application = application;
    }
    
    public void RegisterConvertMethod(){
        On.post("/v1/convert").json((req, resp) -> {
            LocalDateTime start = LocalDateTime.now();
            String from = req.param("from");
            String to = req.param("to");
            Double amount = Double.parseDouble(req.param("amount"));
            Object result = new Object();
            int httpStatus = 0;
            String info = "";
            LocalDateTime end;
            try
            {
                result = this.application.getConvertCurrencyCommand().Execute(from, to, amount);
                httpStatus = 200;
                info = "ok";
            } catch (Exception e) {
                result = e.getMessage();
                info = "Unprocessable Entity";
                httpStatus = 422;
            } finally {
                end = LocalDateTime.now();
            }
            Map<Object, Object> response;
            if (httpStatus == 200)
                response = Response.JSONWithRequestTime(result, httpStatus, info, start, end);
            else 
                response = Response.Error(result, httpStatus, info);
            
            return response;
        });
    }
}
