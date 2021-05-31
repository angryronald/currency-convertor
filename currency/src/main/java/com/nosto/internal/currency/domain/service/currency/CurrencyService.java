/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.domain.service.currency;

import com.nosto.internal.currency.domain.Currency;
import com.nosto.internal.currency.infrastructure.external.ConverterExternal;
import com.nosto.internal.currency.infrastructure.external.ExternalCurrency;
/**
 *
 * @author angryronald
 */
public class CurrencyService implements CurrencyServiceInterface {
    private ConverterExternal ConverterService;
    
    public CurrencyService(ConverterExternal ConverterService) {
        this.ConverterService = ConverterService;
    }
    
    public Currency Convert(Currency source, String targetCode) throws Exception {
        Currency result = null;
        try {
           ExternalCurrency externalSource = new ExternalCurrency(source.GetCode(), source.GetAmount());
           ExternalCurrency externalResult = this.ConverterService.Convert(externalSource, targetCode);
           
           if (result != null) {
               result = new Currency(externalResult.GetCode(), externalResult.GetAmount());
           } 
        } catch (Exception e) {
            throw e;
        }
        
        return result;
    }
}
