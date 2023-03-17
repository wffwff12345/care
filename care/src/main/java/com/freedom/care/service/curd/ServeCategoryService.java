package com.freedom.care.service.curd;

import com.freedom.care.entity.crud.ServeCategoryEntity;
import com.freedom.care.repository.crud.ServeCategoryRepository;
import com.freedom.common.service.BaseCrudPageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServeCategoryService extends BaseCrudPageService<ServeCategoryEntity, UUID> {

    @Resource
    ServeCategoryRepository repository;

    @Override
    protected ServeCategoryRepository getRepository() {
        return repository;
    }
}
