package com.example.xblog.util;

import org.springframework.context.annotation.Conditional;

import javax.annotation.Resource;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatLock {
    enum Type {PARAM,TOKEN}

    Type limitType() default Type.PARAM;

    long lockTime() default 5;

    public String message()  default "111111111111";
}
