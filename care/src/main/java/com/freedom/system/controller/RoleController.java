package com.freedom.system.controller;

import com.freedom.common.controller.BaseCrudController;
import com.freedom.system.entity.crud.RoleEntity;
import com.freedom.system.service.crud.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseCrudController<RoleEntity, UUID> {

    @Resource
    RoleService service;

    @Override
    protected RoleService getService() {
        return service;
    }
}
