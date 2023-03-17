package com.freedom.system.service.crud;

import com.freedom.common.repository.CrudPageRepository;
import com.freedom.common.service.BaseCrudPageService;
import com.freedom.system.entity.crud.RoleEntity;
import com.freedom.system.repository.crud.RoleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService extends BaseCrudPageService<RoleEntity, UUID> {

    @Resource
    RoleRepository repository;

    @Override
    protected RoleRepository getRepository() {
        return repository;
    }
}
