/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.application.Query;

import com.nosto.internal.currency.domain.service.currency.CurrencyServiceInterface;
import com.nosto.internal.currency.domain.Currency;
/**
 *
 * @author angryronald
 */
public class ConvertCurrency {
    private CurrencyServiceInterface service;
    
    public ConvertCurrency(CurrencyServiceInterface service) {
        this.service = service;
    }
    
    public Currency Execute(String sourceCurrencyCode, String targetCurrencyCode, Double amount) throws Exception {
        Currency source = new Currency(sourceCurrencyCode, amount);
        Currency result = this.service.Convert(source, targetCurrencyCode);
        
        return result;
    }
}
