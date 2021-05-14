package com.example.javahomework.w7_sql.t11.service;

import com.example.javahomework.w7_sql.t11.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    UserDTO getOne(Serializable id);

    void save(UserDTO entity);

    void remove(Serializable id);


    List<UserDTO> getList();
}