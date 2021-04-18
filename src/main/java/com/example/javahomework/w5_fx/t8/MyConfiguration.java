package com.example.javahomework.w5_fx.t8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.getStudents().add(student1());
        klass.getStudents().add(student2());
        return klass;
    }

    @Bean
    public Student student1() {
        Student student = new Student();
        student.setId(11);
        student.setName("张三");
        return student;
    }


    @Bean
    public Student student2() {
        Student student = new Student();
        student.setId(12);
        student.setName("李四");
        return student;
    }
}
