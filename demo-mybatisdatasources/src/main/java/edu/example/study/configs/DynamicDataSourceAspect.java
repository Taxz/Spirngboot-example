package edu.example.study.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Taxz on 2018/4/18.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private final static Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
    private final String[] SELECT = {"select"};

    @Pointcut("execution(* edu.example.study.mapper.*.*(..))")
    public void doAspect(){
    }

    @Before("doAspect()")
    public void befores(JoinPoint point) {
        boolean flag = isContain(point.getSignature().getName());
        if (flag) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
        } else {
            DynamicDataSourceContextHolder.useMasterDataSource();
        }
    }

    @After("doAspect()")
    public void afters(JoinPoint point){
        DynamicDataSourceContextHolder.remove();
    }

    public boolean isContain(String name) {
        for (String str : SELECT) {
            if (name.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
