package com.freedom.care.controller;

import com.freedom.care.entity.crud.ServeCategoryEntity;
import com.freedom.care.entity.crud.ServeEntity;
import com.freedom.care.service.curd.ServeService;
import com.freedom.common.controller.BaseCrudController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/serve")
@Tag(name = "服务管理")
public class ServeController extends BaseCrudController<ServeEntity, UUID> {

    @Resource
    ServeService service;

    @Override
    protected ServeService getService() {
        return service;
    }
}
