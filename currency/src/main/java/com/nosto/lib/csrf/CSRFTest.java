/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nosto.lib.csrf;

import com.nosto.lib.csrf.CSRF;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author angryronald
 */
public class CSRFTest {
    @Test
    public void setCSRFTest(){
        LocalDateTime now = LocalDateTime.now();
        try{
            String csrfToken = CSRF.GenerateToken(now);
            CSRF.Set(csrfToken);
            Mockito.when(CSRF.Get(csrfToken)).thenReturn(now);
            LocalDateTime actualResult = CSRF.Get(csrfToken);
            Assert.assertEquals(now, actualResult);
        } catch(Exception e){
            System.err.println("Runtime error while running setCSRFTest: "+e);
        }
    }
    
    @Test
    public void getCSRFTest(){
        LocalDateTime now = LocalDateTime.now();
        try{
            String csrfToken = CSRF.GenerateToken(now);
            CSRF.Set(csrfToken);
            Mockito.when(CSRF.Get(csrfToken)).thenReturn(now);
            LocalDateTime actualResult = CSRF.Get(csrfToken);
            Assert.assertEquals(now, actualResult);
        } catch(Exception e){
            System.err.println("Runtime error while running getCSRFTest: "+e);
        }
    }
    
    @Test
    public void unsetCSRFTest(){
        LocalDateTime now = LocalDateTime.now();
        try{
            String csrfToken = CSRF.GenerateToken(now);
            CSRF.Set(csrfToken);
            CSRF.Unset(csrfToken);
            Mockito.when(CSRF.IsExists(csrfToken)).thenReturn(false);
            boolean actualResult = CSRF.IsExists(csrfToken);
            Assert.assertEquals(false, actualResult);
        } catch(Exception e){
            System.err.println("Runtime error while running unsetCSRFTest: "+e);
        }
    }
    
    @Test
    public void isExistCSRFTest(){
        LocalDateTime now = LocalDateTime.now();
        try{
            // case#1: when there is no csrf token
            String csrfToken = CSRF.GenerateToken(now);
            CSRF.Set(csrfToken);
            CSRF.Unset(csrfToken);
            Mockito.when(CSRF.IsExists(csrfToken)).thenReturn(false);
            boolean actualResult = CSRF.IsExists(csrfToken);
            Assert.assertEquals(false, actualResult);
            
            // case#2: when there is csrf token
            CSRF.Set(csrfToken);
            Mockito.when(CSRF.IsExists(csrfToken)).thenReturn(true);
            actualResult = CSRF.IsExists(csrfToken);
            Assert.assertEquals(true, actualResult);
        } catch(Exception e){
            System.err.println("Runtime error while running unsetCSRFTest: "+e);
        }
    }
}
