package com.example.javahomework.w7_sql.t10.bo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javahomework.w7_sql.t10.bo.UserBO;
import com.example.javahomework.w7_sql.t10.dao.UserDAO;
import com.example.javahomework.w7_sql.t10.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2021-05-13 11:15:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBOImpl extends ServiceImpl<UserDAO, User> implements UserBO {

}