package com.freedom.care.controller;

import com.freedom.care.service.mini.AgedService;
import com.freedom.common.model.ResultModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aged")
public class AgedController {

    @Resource
    AgedService service;

    @GetMapping("serve-list")
    public ResultModel getServeList() {
        return service.getServeList();
    }
}
