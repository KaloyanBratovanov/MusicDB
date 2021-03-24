package com.ex.musicdb.aop;

import com.ex.musicdb.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.ex.musicdb.web.AlbumController.details(..))")
    public void detailsPointCut(){

    }

    @After("detailsPointCut()")
    public void afterAdvice(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        Long albumId = (Long) args[0];
        String action = joinPoint.getSignature().getName();

        logService.createLog(action, albumId);
    }
}
