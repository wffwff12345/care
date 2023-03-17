package com.freedom.care.controller;

import com.freedom.care.entity.crud.ServeCategoryEntity;
import com.freedom.care.service.curd.ServeCategoryService;
import com.freedom.common.controller.BaseCrudController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/serve-category")
public class ServeCategoryController extends BaseCrudController<ServeCategoryEntity, UUID> {
    @Resource
    ServeCategoryService service;

    @Override
    protected ServeCategoryService getService() {
        return service;
    }
}
