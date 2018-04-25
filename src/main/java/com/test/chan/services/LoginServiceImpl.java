/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.services;

import com.test.chan.daos.*;
import com.test.chan.beans.LoginUser;
import com.test.chan.mappers.UserMAP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CHANMYAETHU
 */

@Service
public class LoginServiceImpl implements LoginService{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserMAP userMAP;
    
    @Override
    public LoginUser loadUserByUsername(String userId) throws Exception {
        LoginUser result = null;
        try {
            result = userMAP.findByUsername(userId);
        } catch (Exception e) {
            logger.error("loadUserByUsername function has been failed.");
            throw new Exception(e.getMessage());
        }
        return result;
    }
    
}
