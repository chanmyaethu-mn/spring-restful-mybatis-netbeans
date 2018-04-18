/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author CHANMYAETHU
 */
@Aspect
@Component
public class ApplicationLogger {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationLogger.class.getSimpleName());

    @Pointcut(value = "execution(public * *(..)) && (within(com.test.chan.services..*))")
    public void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object logService(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();

        LOGGER.debug("AROUND START ----------- " + signature.getName());

        Object object = pjp.proceed();

        LOGGER.debug("AROUND END ----------- " + signature.getName());
        
        return object;
    }
}
