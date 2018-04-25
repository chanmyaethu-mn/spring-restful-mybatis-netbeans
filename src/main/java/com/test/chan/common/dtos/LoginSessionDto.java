/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.common.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author CHANMYAETHU
 */
public class LoginSessionDto {
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    
    private Long lastLoginTimeMilliSecs;

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken() {
        return usernamePasswordAuthenticationToken;
    }

    public void setUsernamePasswordAuthenticationToken(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        this.usernamePasswordAuthenticationToken = usernamePasswordAuthenticationToken;
    }

    public Long getLastLoginTimeMilliSecs() {
        return lastLoginTimeMilliSecs;
    }

    public void setLastLoginTimeMilliSecs(Long lastLoginTimeMilliSecs) {
        this.lastLoginTimeMilliSecs = lastLoginTimeMilliSecs;
    }
    
    
}
