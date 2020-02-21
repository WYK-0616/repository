package com.offcn.aspect;

import com.offcn.comparision.controller.ComparisionController;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLogAspect {

    private Logger logger = Logger.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(* com.offcn.*.controller..*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint point){
        String methodName = point.getSignature().getDeclaringTypeName();
        String ClassName = point.getTarget().getClass().getName();

        logger.info("访问的接口名是："+ClassName+",方法名是："+methodName);
    }
}
