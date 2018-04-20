/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.daos;

import com.test.chan.beans.LoginUser;
import com.test.chan.mappers.UserMAP;
import javax.transaction.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CHANMYAETHU
 */

@Transactional(propagation = Propagation.REQUIRED)
@Repository(value = "UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMAP userMAP;

    @Override
    public LoginUser loadUserByUsername(String userId) {
        LoginUser result = null;
        try {
            result = userMAP.findByUsername(userId);
        } catch (Exception e) {
            logger.error("loadUserByUsername function has been failed.");
            //throw new SystemException(e.getErrorCode(), "loadUserByUsername function has been failed.", e);
        }
        return result;
    }

    public UserDetails loadUserById(String userId) {
        UserDetails result = null;
        try {
            result = userMAP.findById(userId);
        } catch (Exception e) {
            logger.error("loadUserById function has been failed.");
            //throw new SystemException(e.getErrorCode(), "loadUserById function has been failed.", e);
        }
        return result;
    }

}
