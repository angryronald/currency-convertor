/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.csrf;

import com.nosto.lib.http.Response;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.rapidoid.setup.On;

/**
 *
 * @author angryronald
 */
public class CSRFHandler {
    public static void RegisterCSRFEndpoint(){
        On.get("/v1/csrf").json((req, resp) -> {
            LocalDateTime now = LocalDateTime.now();
            String csrfToken = CSRF.GenerateToken(now);
            CSRF.Set(csrfToken);
            CSRF csrf = new CSRF(csrfToken, now, now.plus(5, ChronoUnit.MINUTES));
            
            Map<Object, Object> response = Response.JSONWithRequestTime(csrf, 200, "ok", now, LocalDateTime.now());
            
            return response;
        });
    }
}
