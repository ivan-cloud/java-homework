package com.example.javahomework.w7_sql.t11.entity;


import com.example.javahomework.w7_sql.t11.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class UserBOCovert {


    public User convert2DO(UserDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public UserDTO convert2DTO(User entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}