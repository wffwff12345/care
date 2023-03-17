package com.freedom.care.controller;

import com.freedom.care.entity.crud.ServeCategoryEntity;
import com.freedom.care.entity.crud.ServeEntity;
import com.freedom.care.service.curd.ServeCategoryService;
import com.freedom.care.service.curd.ServeService;
import com.freedom.common.controller.BaseCrudController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/serve")
public class ServeController extends BaseCrudController<ServeEntity, UUID> {

    @Resource
    ServeService service;

    @Override
    protected ServeService getService() {
        return service;
    }
}
