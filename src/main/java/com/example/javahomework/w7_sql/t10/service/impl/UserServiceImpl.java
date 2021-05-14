package com.example.javahomework.w7_sql.t10.service.impl;


import com.example.javahomework.w7_sql.t10.bo.UserBO;
import com.example.javahomework.w7_sql.t10.dto.UserDTO;
import com.example.javahomework.w7_sql.t10.entity.User;
import com.example.javahomework.w7_sql.t10.entity.UserBOCovert;
import com.example.javahomework.w7_sql.t10.service.UserService;
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
    public List<UserDTO> getList() {
        List<User> list = bo.list();
        List<UserDTO> result = new ArrayList<>();
        for (User user : list) {
            result.add(covert.convert2DTO(user));
        }
        return result;
    }

}