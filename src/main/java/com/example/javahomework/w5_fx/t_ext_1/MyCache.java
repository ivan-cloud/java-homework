package com.example.javahomework.w5_fx.t_ext_1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyCache {

    int value();
}
