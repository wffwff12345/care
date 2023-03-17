package com.freedom.system.controller;

import com.freedom.common.helper.JwtHelper;
import com.freedom.common.model.ResultModel;
import com.freedom.system.model.LoginModel;
import com.freedom.system.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    LoginService service;

    @PostMapping("")
    public ResultModel login(@RequestBody LoginModel model) {
        return service.login(model);
    }

}
