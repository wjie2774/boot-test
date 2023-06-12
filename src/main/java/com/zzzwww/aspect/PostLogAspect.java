package com.zzzwww.aspect;


import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.zzzwww.post.dao.PostLogMapper;
import com.zzzwww.post.dto.entity.PostLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Order(20)
public class PostLogAspect {

    @Autowired
    private PostLogMapper postLogMapper;

    @Pointcut("@annotation(com.zzzwww.aspect.annotation.PostLog)")
    public void pointcut(){}


    @Around("pointcut()")
    public Object postLog(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        PostLog postLog = new PostLog();
        postLog.setMethodName(method.getName());
        postLog.setParam(JSON.toJSONString(pjp.getArgs()));
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        postLog.setIp(ServletUtil.getClientIP(request, ""));
        postLog.setUrl(request.getRequestURL().toString());
        Object result = pjp.proceed();
        postLog.setResult(JSON.toJSONString(result));
        postLog.setCreateTime(new Date());
        postLogMapper.insert(postLog);
        return result;
    }
}
