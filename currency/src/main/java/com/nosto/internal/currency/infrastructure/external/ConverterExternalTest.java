/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.infrastructure.external;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nosto.internal.currency.infrastructure.external.http.ConverterHttp;
import com.nosto.lib.http.Request;
/**
 *
 * @author angryronald
 */
public class ConverterExternalTest {
    private ConverterExternal converterExternal;
    
    @Before
    public void init()
    {
        Request client = new Request();
        client.AddParameter("access_key", "d774eeef0d7928e7920a53050c0623f1");
        client.SetBaseURL("http://api.exchangeratesapi.io/v1");
        this.converterExternal = new ConverterHttp(client);
    }
    
    @Test
    public void convertTest() {
        // on this test we only focusing on the code since the rate is always changing
        String targetCode = "USD";
        ExternalCurrency source = new ExternalCurrency("EUR",1.0);
        ExternalCurrency expectedResult = new ExternalCurrency("USD", 0.0);
        try{
           ExternalCurrency actualResult = this.converterExternal.Convert(source, targetCode);
           Assert.assertEquals(expectedResult.getCode(), actualResult.getCode());
        } catch(Exception e) {
            System.err.println("Runtime error while running convertTest: "+e);
        }
    }
}
