package com.test.chan.common.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLogger {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
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
    @Before("controller() && allMethod()")
    public void logBefore(JoinPoint joinPoint) {

        log.debug("<<<<<<<<<< START >>>>>>>>>>");

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
        log.debug("<<<<<<<<<< END >>>>>>>>>>");
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
    @Around("controller() && allMethod() && args(..,request)")
    public Object logAround(ProceedingJoinPoint joinPoint, HttpServletRequest request) throws Throwable {

         String ipAddress = "NONE";

        try {
            if (null != request) {
                ipAddress = request.getRemoteAddr();
            }
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String arguments = Arrays.toString(joinPoint.getArgs());
            String targetClass = joinPoint.getTarget().getClass().getName();
            Object result = joinPoint.proceed();
            log.debug("IP : " + ipAddress);
            log.debug("Class Name : " + className);
            log.debug("Method Name : " + methodName);
            log.debug("Argument : " + arguments);
            log.debug("Target Class : " + targetClass);
            return result;
        } catch (Exception e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
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
