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
public class ExternalCurrency {
    private String Code;
    private Double Amount;
    
    public ExternalCurrency(String code, Double amount) {
        this.Code = code;
        this.Amount = amount;
    }
    
    public void setCode(String code) {
        this.Code = code;
    }
    
    public void setAmount(Double amount) {
        this.Amount = amount;
    }
    
    public String getCode() {
        return this.Code;
    }
    
    public Double getAmount() {
        return this.Amount;
    }
}
