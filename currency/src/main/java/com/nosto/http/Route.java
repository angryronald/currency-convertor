/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.http;

import com.nosto.container.Container;
import com.nosto.lib.csrf.CSRFHandler;

/**
 *
 * @author angryronald
 */
public class Route {
    public static void RegisterAllRoute(Container container){
        container.GetEndpoint().RegisterConvertMethod();
        CSRFHandler.RegisterCSRFEndpoint();
    }
}
