package edu.example.study.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Taxz on 2018/4/19.
 */
@Aspect
@Component
public class HttpLog {
    private static Logger logger = LoggerFactory.getLogger(HttpLog.class);

    private ThreadLocal<Long> time = new ThreadLocal();

    @Pointcut("execution(* edu.example.study.controller.*.*(..))")
    public void httpLog(){

    }

    @Before("httpLog()")
    public void before(JoinPoint joinPoint) {
        time.set(System.currentTimeMillis());
        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attribute.getRequest();
        System.out.println("zhxing");
        //URL

        logger.info("url:{}", request.getRequestURL());
        //Request method
        logger.info("method:{}", request.getMethod());
        //IP
        logger.info("ip:{}", request.getRemoteAddr());
        //Class method name
        logger.info("method:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // Argument
        logger.info("args:{}", JSONUtil.toString(joinPoint.getArgs()));
    }

    @After("httpLog()")
    public void after(){
        logger.info("request cost time:{} ms", System.currentTimeMillis() - time.get());
    }

    @AfterReturning(returning = "object",pointcut = "httpLog()")
    public void afterRunning(Object object){
        logger.info("response:{}", JSONUtil.toString(object));
    }
}
