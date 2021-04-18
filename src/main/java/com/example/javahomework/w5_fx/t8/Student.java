package com.example.javahomework.w5_fx.t8;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;

@Data
@ToString
public class Student implements BeanNameAware {
    private int id;
    private String name;
    private String beanName;
}
