package com.zzzwww.aspect;

import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzzwww.aspect.annotation.CheckId;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(10)
public class CheckIdAspect {

    private static final String SQL = "select count(*) from %s where %s = %s";
    private static final String SQL_DELETED = "select count(*) from %s where %s = %s and deleted = 0";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Pointcut("@annotation(com.zzzwww.aspect.annotation.CheckId)")
    public void pointcut(){}

    @Before("pointcut()")
    @SuppressWarnings("all")
    public void checkIdAspect(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        CheckId checkId = method.getAnnotation(CheckId.class);
        String id = this.buildExternalIdId(checkId, jp, method);
        String idAlias = checkId.idAlias();
        Class<?> clzss = checkId.clazz();
        TableName annotation = clzss.getAnnotation(TableName.class);
        String tableName = annotation.value();
        long count;
        String sql;
        if (BooleanUtil.isTrue(checkId.isDeletedField())) {
            sql = String.format(SQL_DELETED, tableName ,idAlias, id);
        } else {
            sql = String.format(SQL, tableName ,idAlias, id);
        }
        count = jdbcTemplate.queryForObject(sql, Long.class);
        Assert.isTrue(count > 0, checkId.errorMessage());
    }

    private String buildExternalIdId(CheckId anno, JoinPoint pjp, Method method) {
        ExpressionParser parser = new SpelExpressionParser();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(method);
        Object[] args = pjp.getArgs();
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
        Expression expression = parser.parseExpression(anno.id());
        return expression.getValue(context, String.class);
    }
}
