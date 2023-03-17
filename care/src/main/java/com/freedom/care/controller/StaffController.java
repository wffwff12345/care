package com.freedom.care.controller;

import com.freedom.care.service.mini.AgedService;
import com.freedom.care.service.mini.StaffService;
import com.freedom.common.model.ResultModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@Tag(name = "小程序职员管理端")

public class StaffController {

    @Resource
    StaffService service;
    @Operation(summary = "获取订单列表",description ="" )
    @GetMapping("order-list")
    public ResultModel getServeList() {
        return service.getOrderList();
    }
}
