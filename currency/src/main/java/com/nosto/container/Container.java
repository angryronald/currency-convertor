/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.container;

import com.nosto.lib.http.Request;
import com.nosto.internal.currency.infrastructure.external.http.ConverterHttp;
import com.nosto.internal.currency.domain.service.currency.CurrencyService;
import com.nosto.internal.currency.application.Application;
import com.nosto.internal.currency.application.Query.ConvertCurrency;
import com.nosto.internal.currency.endpoint.Endpoint;
import com.nosto.config.Config;

/**
 *
 * @author angryronald
 */
public class Container {
    private CurrencyService currencyService;
    private Application application;
    private Endpoint endpoint;
    
    public Container(Config config){
        Request client = new Request();
        client.AddParameter("access_key", config.GetExchangeRatesAPIAccessKey());
        client.SetBaseURL(config.GetExchangeRatesAPIURL());
        ConverterHttp converter = new ConverterHttp(client);
        this.currencyService = new CurrencyService(converter);
        ConvertCurrency convertCurrency = new ConvertCurrency(currencyService);
        this.application = new Application(convertCurrency);
        this.endpoint = new Endpoint(application);
    }
    
    public Endpoint GetEndpoint(){
        return this.endpoint;
    }
    
    public CurrencyService GetCurrencyService(){
        return this.currencyService;
    }
    
    public Application GetApplication(){
        return this.application;
    }
}
