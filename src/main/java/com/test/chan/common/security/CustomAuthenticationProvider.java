/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.common.security;

import com.test.chan.beans.LoginUser;
import com.test.chan.common.helper.LoginHelper;
import com.test.chan.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private LoginService loginService;

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        LoginUser loginUser = null;
//        try {
//            loginUser = (LoginUser) loginService.loadUserByUsername(name);
//        } catch (Exception ex) {
//            Logger.getLogger(CustomAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (null != loginUser && LoginHelper.login(loginUser, password)) {
//            return new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//        } else {
//            throw new BadCredentialsException("Authentication failed for " + name);
//        }
//
//    }
    
        @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        LoginUser loginUserDto = new LoginUser();

        try {
            loginUserDto = (LoginUser) loginService.loadUserByUsername(name);
        } catch (Exception ex) {
            logger.debug("authenticate method is failed.", ex);
        }

        if (LoginHelper.login(loginUserDto, password)) {

            return new UsernamePasswordAuthenticationToken(loginUserDto, null, loginUserDto.getAuthorities());

            //return resultAuth;
//            if (USER_LOGIN_MAP.containsKey(name)) {
//                LoginSessionDto loginSessionDto = USER_LOGIN_MAP.get(name);
//                Long lastLoginTimeMilliSecs = loginSessionDto.getLastLoginTimeMilliSecs();
//                Long curTimeMilliSecs = System.currentTimeMillis();
//                Long diffTimeMilliSecs = curTimeMilliSecs - lastLoginTimeMilliSecs;
//                Long diffTimeSecs = TimeUnit.MILLISECONDS.toSeconds(diffTimeMilliSecs);
//
//                if (diffTimeSecs <= 120) {
//                    return (Authentication) loginSessionDto.getUsernamePasswordAuthenticationToken();
//                }
//            } else {
//
//                LoginSessionDto loginSessionDto = new LoginSessionDto();
//                loginSessionDto.setLastLoginTimeMilliSecs(System.currentTimeMillis());
//                loginSessionDto.setUsernamePasswordAuthenticationToken((UsernamePasswordAuthenticationToken) resultAuth);
//
//                USER_LOGIN_MAP.put(name, loginSessionDto);
//            }
//
            //return (Authentication) resultAuth;

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
