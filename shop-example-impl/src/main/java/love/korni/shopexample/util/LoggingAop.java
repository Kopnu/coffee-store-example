/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP for logging the execution of service methods.
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Aspect
@Component
public class LoggingAop {

    @Pointcut("within(love.korni.shopexample.resource.impl..*) && @within(org.springframework.web.bind.annotation.RestController)")
    public void allResources() {
    }

    @Pointcut("within(love.korni.shopexample.service.impl..*) && @within(org.springframework.stereotype.Service)")
    public void allServices() {
    }

    @Pointcut("allResources() || allServices()")
    public void allResourcesAndServices() {
    }

    @Around("allResourcesAndServices()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("{} {}() - start: args [{}]",
                    point.getSignature().getDeclaringType().getSimpleName(),
                    point.getSignature().getName(),
                    Arrays.toString(point.getArgs()));
        }

        Object returnValue = point.proceed();

        if (log.isDebugEnabled()) {
            log.debug("{} {}() - end: return value [{}]",
                    point.getSignature().getDeclaringType().getSimpleName(),
                    point.getSignature().getName(),
                    returnValue);
        }
        return returnValue;
    }

}
