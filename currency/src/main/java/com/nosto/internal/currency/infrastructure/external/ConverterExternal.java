/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.infrastructure.external;

/**
 *
 * @author angryronald
 */
public interface ConverterExternal {
    public ExternalCurrency Convert(ExternalCurrency source, String code) throws Exception;
}
