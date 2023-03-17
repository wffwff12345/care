package com.freedom.care.service.curd;

import com.freedom.care.entity.crud.ServeEntity;
import com.freedom.care.repository.crud.ServeRepository;
import com.freedom.common.service.BaseCrudPageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServeService extends BaseCrudPageService<ServeEntity, UUID> {

    @Resource
    ServeRepository repository;

    @Override
    protected ServeRepository getRepository() {
        return repository;
    }
}
