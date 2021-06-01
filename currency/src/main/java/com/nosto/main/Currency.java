package com.nosto.main;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.rapidoid.setup.On;
import com.nosto.config.Config;
import com.nosto.container.Container;

import com.nosto.http.Route;
/**
 *
 * @author angryronald
 */
public class Currency {
    public static void main (String[] args) {
        System.out.println("Currency.main()");
        
        Config config = new Config();
        On.address(config.GetRegisteredHost()).port(config.GetExposedPort());
        
        Container container = new Container(config);
        Route.RegisterAllRoute(container);
    }
}
