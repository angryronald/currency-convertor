/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import org.rapidoid.u.U;
import java.util.Map;

/**
 *
 * @author angryronald
 */
public class Response {
    public static Map<Object, Object> JSON(Object data, Integer code, String info) {
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("data", data);
        result.put("code", code);
        result.put("info", info);
        
        return result;
    }
    
    public static Map<Object, Object> Error(Object data, Integer code, String info) {
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("data", data);
        result.put("code", code);
        result.put("info", info);
        
        System.err.println("Error: "+data);
        
        return result;
    }
    
    public static Map<Object, Object> JSONWithRequestTime(Object data, Integer code, String info, LocalDateTime start, LocalDateTime end) {
        Map<Object, Object> metadata = new HashMap<Object, Object>();
        metadata.put("start", start);
        metadata.put("end", end);
        metadata.put("request_took", ChronoUnit.MILLIS.between(start, end));
        metadata.put("request_measure","time_measure.millisecond");
        
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("data", data);
        result.put("code", code);
        result.put("info", info);
        result.put("metadata", metadata);
        
        System.err.println("Error: "+info);
        
        return result;
    }
}
