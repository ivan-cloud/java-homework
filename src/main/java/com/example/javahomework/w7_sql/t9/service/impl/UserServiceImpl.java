package com.example.javahomework.w7_sql.t9.service.impl;


import com.example.javahomework.w7_sql.t9.bo.UserBO;
import com.example.javahomework.w7_sql.t9.config.ReadOnly;
import com.example.javahomework.w7_sql.t9.dto.UserDTO;
import com.example.javahomework.w7_sql.t9.entity.User;
import com.example.javahomework.w7_sql.t9.entity.UserBOCovert;
import com.example.javahomework.w7_sql.t9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBO bo;
    @Autowired
    private UserBOCovert covert;

    @Override
    @ReadOnly
    public UserDTO getOne(Serializable id) {
        return covert.convert2DTO(bo.getById(id));
    }

    @Override
    public void save(UserDTO entity) {
        bo.save(covert.convert2DO(entity));
    }

    @Override
    public void remove(Serializable id) {
        bo.removeById(id);
    }

    @Override
    @ReadOnly
    public List<UserDTO> getList() {
        List<User> list = bo.list();
        List<UserDTO> result = new ArrayList<>();
        for (User user : list) {
            result.add(covert.convert2DTO(user));
        }
        return result;
    }

}