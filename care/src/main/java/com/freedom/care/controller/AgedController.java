package com.freedom.care.controller;

import com.freedom.care.service.mini.AgedService;
import com.freedom.common.model.ResultModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/aged")
@Tag(name = "小程序老人管理端")
public class AgedController {

    @Resource
    AgedService service;
    @Operation(summary = "获取服务列表",description ="author:wcp" )
    @GetMapping("serve-list")
    public ResultModel getServeList() {
        return service.getServeList();
    }

    @Operation(summary = "获取单个服务",description ="" )
    @GetMapping("serve/{id}")
    public ResultModel getServeById(@Parameter(name = "id", description = "服务编号ID", required = true) @PathVariable UUID id) {
        return service.getServeById(id);
    }


}
