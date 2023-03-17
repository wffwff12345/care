package com.freedom.system.controller;

import com.freedom.common.model.ResultModel;
import com.freedom.system.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/my")
public class MyController {

    @Resource
    LoginService service;

    @GetMapping("refreshToken")
    public ResultModel refreshToken(Principal principal) {
        return service.refreshToken(principal.getName());
    }
}
