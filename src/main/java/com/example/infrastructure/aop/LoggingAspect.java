package com.example.infrastructure.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.application..*ServiceImpl.*(..))", throwing = "e")
    public void logAfterThrowingInService(JoinPoint joinPoint, Throwable e) {
        Logger log = getLogger(joinPoint);
        log.error(
                "\nLOCATION: {}\nCAUSE: {}\nEXCEPTION: {}\n",
                joinPoint.getSignature().getDeclaringTypeName(),
                e.getCause() != null ? e.getCause() : "No cause",
                e.getMessage()
        );
    }
}
