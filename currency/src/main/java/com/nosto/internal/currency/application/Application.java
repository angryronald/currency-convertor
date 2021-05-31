/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.application;

import com.nosto.internal.currency.application.Query.ConvertCurrency;
/**
 *
 * @author angryronald
 */
public class Application {
    private ConvertCurrency convertCurrencyCommand;
    
    public Application(ConvertCurrency convertCurrencyCommand) {
        this.convertCurrencyCommand = convertCurrencyCommand;
    }
    
    public ConvertCurrency getConvertCurrencyCommand() {
        return this.convertCurrencyCommand;
    }
}
