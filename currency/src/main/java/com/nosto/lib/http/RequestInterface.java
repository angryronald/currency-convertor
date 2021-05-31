/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.http;

import java.net.http.HttpResponse;
import java.util.Map;

/**
 *
 * @author angryronald
 */

public interface RequestInterface {
    public String DoRequest(Method method, String urlPath, Map<String, String> parameters, Map<String, String> requestBody) throws Exception;
}
