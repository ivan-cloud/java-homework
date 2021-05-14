package com.example.javahomework.w7_sql.t11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户(User)表实体类
 *
 * @author makejava
 * @since 2021-05-13 11:15:47
 */
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -5505555309579495057L;
    //用户编号
    private Integer id;
    //用户姓名
    private String name;
    //手机号
    private String tel;
}