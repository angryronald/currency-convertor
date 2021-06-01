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
import org.rapidoid.setup.On;
import com.nosto.lib.http.Middleware;
import com.nosto.lib.csrf.CSRFException;
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
            Map<String, String> queryParams = req.params();
            
            String from = "";
            if (queryParams.containsKey("from")){
                from = queryParams.get("from");
            }
            
            String to = req.param("to");
            if (queryParams.containsKey("to")){
                to = queryParams.get("to");
            }
            
            Double amount = 0.0;
            if (queryParams.containsKey("amount") && queryParams.get("amount") != ""){
                amount = Double.parseDouble(queryParams.get("amount"));
            }
            
            String csrfToken = "";
            if (queryParams.containsKey("csrf")){
                csrfToken = queryParams.get("csrf");
            }
            
            Object result = new Object();
            int httpStatus = 0;
            String info = "";
            LocalDateTime end;
            
            if (from == "") {
                httpStatus = 400;
                info = "Bad Request";
                result = "'from' is mandatory field";
                return Response.Error(result, httpStatus, info);
            }
            
            if (to == ""){
                httpStatus = 400;
                info = "Bad Request";
                result = "'to' is mandatory field";
                return Response.Error(result, httpStatus, info);
            }
            
            try
            {
                if (Middleware.IsCSRFValid(csrfToken)){
                    result = this.application.getConvertCurrencyCommand().Execute(from, to, amount);
                    httpStatus = 200;
                    info = "ok";
                }
            } catch(CSRFException e){
                result = e.getMessage();
                info = "Bad Request";
                httpStatus = 400;
            }catch (Exception e) {
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
