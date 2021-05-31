/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.internal.currency.domain;

/**
 *
 * @author angryronald
 */
public class Currency {
    private String Code;
    private Double Amount;
    
    public Currency(String code, Double amount) {
        this.Code = code;
        this.Amount = amount;
    }
    
    public void SetCode(String code) {
        this.Code = code;
    }
    
    public void SetAmount(Double amount) {
        this.Amount = amount;
    }
    
    public String GetCode() {
        return this.Code;
    }
    
    public Double GetAmount() {
        return this.Amount;
    }
}
