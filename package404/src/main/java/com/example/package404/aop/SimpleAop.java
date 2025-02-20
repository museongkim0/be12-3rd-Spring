package com.example.package404.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SimpleAop {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.example.package404..*.*(..))")
    private void pointCut() {
        log.info("pointCut");
    }

    @Before("pointCut()") // pointCut() 에서 지정한 곳의 기능들이 실행 되기 전에 실행될 기능
    public void before(JoinPoint joinPoint) {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        log.info("{} - {} - 실행되기전", className, methodName);
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String className =  joinPoint.getTarget().getClass().getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("{} - {} - {}ms", className, methodName, stopWatch.getTotalTimeMillis());
        }

    }

}
