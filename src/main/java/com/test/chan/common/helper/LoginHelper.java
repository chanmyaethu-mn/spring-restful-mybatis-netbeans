/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.common.helper;

import com.test.chan.beans.LoginUser;

/**
 *
 * @author CHANMYAETHU
 */
public class LoginHelper {

    public static boolean login(LoginUser loginUser, String password) {
        try {
            String hashedPassword = REF_PasswordHash.createHash(password, loginUser.getSalt());
            if (hashedPassword.equals(loginUser.getPwdKey())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

}
