package by.tms.pintusau.todo.configs.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* by.tms.pintusau.lesson41.services.impl.*ServiceImpl.*(..))")
    public void services() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositories() {
    }

    @Pointcut("execution(* by.tms.pintusau.todo.services.impl.*ServiceImpl.create(..))")
    public void serviceCreate() {
    }

    @Before("serviceCreate()")
    public void beforeServiceCreate(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        boolean result = Arrays.stream(args)
                .anyMatch(Objects::isNull);

        if (result) {
            log.error("Found empty argument during method call '{}': {}, validation failed", methodName, args);
            throw new RuntimeException("Validation failed!!!");
        }

        log.debug("Validation passed for method call '{}': {}", methodName, args);
    }

    @Around("services() || repositories()")
    public Object aroundServicesAndRepositories(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = proceedingJoinPoint.getArgs();
        log.debug("Called method '{}.{}' with arguments: {}", className, methodName, args);
        try {
            result = proceedingJoinPoint.proceed();
            log.debug("Method '{}.{}' returned value: {}", className, methodName, result);
        } catch (Throwable e) {
            log.error("Error {} during service method '{}.{}' call", e.getMessage(), className, methodName);
            throw e;
        }
        return result;
    }
}
