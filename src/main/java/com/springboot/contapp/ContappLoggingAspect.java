package com.springboot.contapp;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContappLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.springboot.contapp.controllers.*.*(..))")
    private void forControllersPackage() {
    }

    @Before("forControllersPackage()")
    public void before(JoinPoint joinPoint) {
       String methodName = joinPoint.getSignature().toShortString();
       logger.info("INFO: in @Before Advice. Calling method: " + methodName);
    }

    @AfterReturning(pointcut = "forControllersPackage()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("INFO: in @After Advice. Calling method: " + methodName);
        logger.info("Result is: " + result);
    }

}
