package com.chy.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-23 22:55
 * @Description: 日志记录
 */

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义切面
    @Pointcut("execution(* com.chy.blog.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("-----------doBefore------------- RequestLog: {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("-----------doAfter-------------");
    }

    //记录方法返回的内容
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("-----------doAfterReturn------------- Result: {}", result);

    }

    private class RequestLog{
        private String url;
        private String ip;
        private  String classMethos;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethos, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethos = classMethos;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethos='" + classMethos + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
