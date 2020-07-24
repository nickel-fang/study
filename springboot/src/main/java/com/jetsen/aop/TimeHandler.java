package com.jetsen.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: Nickel Fang
 * @date: 2020/7/6 11:41
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class TimeHandler {
/*    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("代理----前----CurrentTime = {},{},{},{}", System.currentTimeMillis(), method, args, target);
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("代理----后----CurrentTime = {},{},{},{},{}", System.currentTimeMillis(), method, args, target, returnValue);
    }*/

    @Pointcut("@annotation(Time)")
    public void dsPointCut() {

    }

    @Before("dsPointCut()")
    public void before(JoinPoint point) throws Throwable {
        log.info("代理----before----CurrentTime = {},{}", System.currentTimeMillis(), point.getTarget());
    }

    @AfterReturning(value = "dsPointCut()", returning = "result")
    public void afterReturning(JoinPoint point, Object result) throws Throwable {
        log.info("代理----afterReturning----CurrentTime = {},{}", System.currentTimeMillis(), point.getTarget());
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("代理----around----CurrentTime = {},{}", System.currentTimeMillis(), point.getTarget());
        return point.proceed();
    }
}
