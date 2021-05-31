/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.domain.service.currency;

import com.nosto.internal.currency.domain.Currency;
/**
 *
 * @author angryronald
 */
public interface CurrencyServiceInterface {
    public Currency Convert(Currency source, String targetCode) throws Exception;
}
