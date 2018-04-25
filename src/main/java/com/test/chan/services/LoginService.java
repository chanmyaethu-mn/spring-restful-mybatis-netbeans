/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.services;

import com.test.chan.daos.*;
import com.test.chan.beans.LoginUser;

/**
 *
 * @author CHANMYAETHU
 */
public interface LoginService {

    public LoginUser loadUserByUsername(String userId) throws Exception;
}
