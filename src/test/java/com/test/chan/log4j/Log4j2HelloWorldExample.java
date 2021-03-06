/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author CHANMYAETHU
 */
public class Log4j2HelloWorldExample {
    private static final Logger logger = LogManager.getLogger(Log4j2HelloWorldExample.class.getSimpleName());

   public static void main(String[] args) {

      logger.trace("Entering application...");

      logger.info("Hello Log4j2...");
      logger.error("Something is wrong with this code", new Exception("Invalid message"));

      logger.trace("Exiting application...");
   }
}
