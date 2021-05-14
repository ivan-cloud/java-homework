package com.example.javahomework.w7_sql.t9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户(User)表实体类
 *
 * @author makejava
 * @since 2021-05-13 11:15:33
 */
@Data
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = -6385063102100006994L;
    //用户编号
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //用户姓名
    @TableField("name")
    private String name;
    //手机号
    @TableField("tel")
    private String tel;
}