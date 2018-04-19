package com.test.chan.common.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is a class to do logging for method in/out by watching.
 * 
 * @author CHANMYAETHU
 */
@Aspect
@Component
public class ApplicationLogger {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut(value = "execution(public * *(..)) && (within(com.test.chan.services..*))")
    public void serviceMethod() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

    @Pointcut("execution(public * *(..))")
    protected void loggingPublicOperation() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void loggingAllOperation() {
    }

    @Pointcut("within(org.learn.log..*)")
    private void logAnyFunctionWithinResource() {
    }

    /**
     * before -> Any resource annotated with @Controller annotation and all
     * method and function taking HttpServletRequest as first parameter
     *
     * @param joinPoint
     */
    @Before("controller() && allMethod() && args(..,request)")
    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

        log.debug("<<<<<<<<<< START CONTROLLER >>>>>>>>>>");

        String ipAddress = "NONE";

        if (null != request) {
            ipAddress = request.getRemoteAddr();
        }
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.toString(joinPoint.getArgs());
        String targetClass = joinPoint.getTarget().getClass().getName();
        log.debug("Client IP : " + ipAddress);
        log.debug("Class Name : " + className);
        log.debug("Method Name : " + methodName);
        log.debug("Argument : " + arguments);
        log.debug("Target Class : " + targetClass);

    }

    /**
     * After -> All method within resource annotated with @Controller annotation
     * and return a value
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.debug("Method Return value : " + returnValue);
        log.debug("<<<<<<<<<< END CONTROLLER >>>>>>>>>>");
    }

    /**
     * After -> Any method within resource annotated with @Controller annotation
     * throws an exception ...Log it
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        log.error("Cause : " + exception.getCause());
    }

    /**
     * Around -> Any method within resource annotated with @Controller
     * annotation
     *
     * @param joinPoint
     * * @param request
     * @return
     * @throws Throwable
     */
    @Around("controller() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {

            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            Object result = joinPoint.proceed();

            long elapsedTime = System.currentTimeMillis() - start;
            log.debug(" Execution time : " + elapsedTime + " ms");
            return result;
        } catch (Exception e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    /**
     * Logging service method before started
     * 
     * @param joinPoint 
     */
    @Before("serviceMethod()")
    public void logServiceBefore(JoinPoint joinPoint) {

        log.debug("<<<<<<<<<< START SERVICE >>>>>>>>>>");
        log.debug("Method Name : " + joinPoint.getSignature().getName());

    }

    /**
     * Logging after service method has completed
     * 
     * @param joinPoint 
     */
    @After("serviceMethod()")
    public void logServiceAfter(JoinPoint joinPoint) {

        log.debug("<<<<<<<<<< END SERVICE >>>>>>>>>>");

    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}
