package com.example.javahomework.w7_sql.t10.controller;

import com.alibaba.fastjson.JSON;
import com.example.javahomework.w7_sql.t10.dto.UserDTO;
import com.example.javahomework.w7_sql.t10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public String saveUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return "OK";
    }

    @GetMapping
    public String getUser(Integer id) {
        return JSON.toJSONString(userService.getOne(id));
    }

    @GetMapping("list")
    public String getList() {
        return JSON.toJSONString(userService.getList());
    }
}
