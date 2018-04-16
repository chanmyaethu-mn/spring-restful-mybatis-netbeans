/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CHANMYAETHU
 */

@RestController
public class SecureApiTestController {
    
    @RequestMapping(value = "/api/getAuthorName", method = RequestMethod.GET)
    public String getAuthorName() {
        return "Chan Myae Thu";
    }
}
