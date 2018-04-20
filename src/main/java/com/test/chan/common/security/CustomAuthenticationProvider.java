/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.common.security;

import com.test.chan.beans.LoginUser;
import com.test.chan.common.helper.LoginHelper;
import com.test.chan.daos.LoginService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author CHANMYAETHU
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        LoginUser loginUser = null;
        try {
            loginUser = (LoginUser) loginService.loadUserByUsername(name);
        } catch (Exception ex) {
            Logger.getLogger(CustomAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (null != loginUser && LoginHelper.login(loginUser, password)) {
            return new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed for " + name);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
