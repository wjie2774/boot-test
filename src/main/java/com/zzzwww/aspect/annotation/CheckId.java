package com.zzzwww.aspect.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CheckId {

    String id();

    String idAlias() default "id";

    boolean isDeletedField() default true;

    Class<?> clazz();

    String errorMessage() default "Id is invaild.";


}
